package chat.rocket.luk.authentication.twofactor.presentation

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
import chat.rocket.luk.util.extensions.isEmail
import chat.rocket.luk.util.extensions.serverLogoUrl
import chat.rocket.luk.util.retryIO
import chat.rocket.common.RocketChatAuthException
import chat.rocket.common.RocketChatException
import chat.rocket.common.util.ifNull
import chat.rocket.core.internal.rest.login
import chat.rocket.core.internal.rest.loginWithEmail
import chat.rocket.core.internal.rest.me
import chat.rocket.core.model.Myself
import javax.inject.Inject

class TwoFAPresenter @Inject constructor(
    private val view: TwoFAView,
    private val strategy: CancelStrategy,
    private val navigator: AuthenticationNavigator,
    private val tokenRepository: TokenRepository,
    private val localRepository: LocalRepository,
    private val saveCurrentServerInteractor: SaveCurrentServerInteractor,
    private val analyticsManager: AnalyticsManager,
    private val factory: RocketChatClientFactory,
    private val saveAccountInteractor: SaveAccountInteractor,
    val serverInteractor: GetConnectingServerInteractor,
    val settingsInteractor: GetSettingsInteractor
) {
    private val currentServer = serverInteractor.get()!!
    private var settings: PublicSettings = settingsInteractor.get(currentServer)
    private val token = tokenRepository.get(currentServer)

    fun authenticate(
        usernameOrEmail: String,
        password: String,
        twoFactorAuthenticationCode: String
    ) {
        launchUI(strategy) {
            val client = factory.get(currentServer)
            view.showLoading()
            try {
                // The token is saved via the client TokenProvider
                val token = retryIO("login") {
                    if (usernameOrEmail.isEmail()) {
                        client.loginWithEmail(
                            usernameOrEmail,
                            password,
                            twoFactorAuthenticationCode
                        )
                    } else {
                        client.login(usernameOrEmail, password, twoFactorAuthenticationCode)
                    }
                }
                val me = retryIO("me") { client.me() }
                saveAccount(me)
                saveCurrentServerInteractor.save(currentServer)
                tokenRepository.save(currentServer, token)
                localRepository.save(LocalRepository.CURRENT_USERNAME_KEY, me.username)
                analyticsManager.logLogin(
                    AuthenticationEvent.AuthenticationWithUserAndPassword, true
                )
                navigator.toChatList()
            } catch (exception: RocketChatException) {
                if (exception is RocketChatAuthException) {
                    view.alertInvalidTwoFactorAuthenticationCode()
                } else {
                    analyticsManager.logLogin(
                        AuthenticationEvent.AuthenticationWithUserAndPassword, false
                    )
                    exception.message?.let {
                        view.showMessage(it)
                    }.ifNull {
                        view.showGenericErrorMessage()
                    }
                }
            } finally {
                view.hideLoading()
            }
        }
    }

    private fun saveAccount(me: Myself) {
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