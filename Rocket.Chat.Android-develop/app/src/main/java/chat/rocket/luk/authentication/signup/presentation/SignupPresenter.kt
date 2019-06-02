package chat.rocket.luk.authentication.signup.presentation

import chat.rocket.luk.analytics.AnalyticsManager
import chat.rocket.luk.analytics.event.AuthenticationEvent
import chat.rocket.luk.authentication.presentation.AuthenticationNavigator
import chat.rocket.luk.core.lifecycle.CancelStrategy
import chat.rocket.luk.infrastructure.LocalRepository
import chat.rocket.luk.server.domain.GetConnectingServerInteractor
import chat.rocket.luk.server.domain.GetSettingsInteractor
import chat.rocket.luk.server.domain.PublicSettings
import chat.rocket.luk.server.domain.SaveAccountInteractor
import chat.rocket.luk.server.domain.SaveCurrentServerInteractor
import chat.rocket.luk.server.domain.TokenRepository
import chat.rocket.luk.server.domain.favicon
import chat.rocket.luk.server.domain.model.Account
import chat.rocket.luk.server.domain.siteName
import chat.rocket.luk.server.domain.wideTile
import chat.rocket.luk.server.infrastructure.RocketChatClientFactory
import chat.rocket.luk.util.extension.launchUI
import chat.rocket.luk.util.extensions.avatarUrl
import chat.rocket.luk.util.extensions.privacyPolicyUrl
import chat.rocket.luk.util.extensions.serverLogoUrl
import chat.rocket.luk.util.extensions.termsOfServiceUrl
import chat.rocket.luk.util.retryIO
import chat.rocket.common.RocketChatException
import chat.rocket.common.util.ifNull
import chat.rocket.core.internal.rest.login
import chat.rocket.core.internal.rest.me
import chat.rocket.core.internal.rest.signup
import chat.rocket.core.model.Myself
import javax.inject.Inject

class SignupPresenter @Inject constructor(
    private val view: SignupView,
    private val strategy: CancelStrategy,
    private val navigator: AuthenticationNavigator,
    private val localRepository: LocalRepository,
    private val serverInteractor: GetConnectingServerInteractor,
    private val saveCurrentServerInteractor: SaveCurrentServerInteractor,
    private val analyticsManager: AnalyticsManager,
    private val factory: RocketChatClientFactory,
    private val saveAccountInteractor: SaveAccountInteractor,
    tokenRepository: TokenRepository,
    settingsInteractor: GetSettingsInteractor
) {
    private val currentServer = serverInteractor.get()!!
    private var settings: PublicSettings = settingsInteractor.get(currentServer)
    private val token = tokenRepository.get(currentServer)

    fun signup(name: String, username: String, password: String, email: String) {
        val client = factory.get(currentServer)
        launchUI(strategy) {
            view.showLoading()
            try {
                // TODO This function returns a user so should we save it?
                retryIO("signup") { client.signup(email, name, username, password) }
                // TODO This function returns a user token so should we save it?
                retryIO("login") { client.login(username, password) }
                val me = retryIO("me") { client.me() }
                saveCurrentServerInteractor.save(currentServer)
                localRepository.save(LocalRepository.CURRENT_USERNAME_KEY, me.username)
                saveAccount(me)
                analyticsManager.logSignUp(
                    AuthenticationEvent.AuthenticationWithUserAndPassword,
                    true
                )
                view.saveSmartLockCredentials(username, password)
                navigator.toChatList()
            } catch (exception: RocketChatException) {
                analyticsManager.logSignUp(
                    AuthenticationEvent.AuthenticationWithUserAndPassword,
                    false
                )
                exception.message?.let {
                    view.showMessage(it)
                }.ifNull {
                    view.showGenericErrorMessage()
                }
            } finally {
                view.hideLoading()

            }
        }
    }

    fun termsOfService() {
        serverInteractor.get()?.let {
            navigator.toWebPage(it.termsOfServiceUrl())
        }
    }

    fun privacyPolicy() {
        serverInteractor.get()?.let {
            navigator.toWebPage(it.privacyPolicyUrl())
        }
    }

    private suspend fun saveAccount(me: Myself) {
        val icon = settings.favicon()?.let {
            currentServer.serverLogoUrl(it)
        }
        val logo = settings.wideTile()?.let {
            currentServer.serverLogoUrl(it)
        }
        val thumb = currentServer.avatarUrl(me.username!!, token?.userId, token?.authToken)
        val account = Account(
            settings.siteName() ?: currentServer,
            currentServer,
            icon,
            logo,
            me.username!!,
            thumb
        )
        saveAccountInteractor.save(account)
    }
}