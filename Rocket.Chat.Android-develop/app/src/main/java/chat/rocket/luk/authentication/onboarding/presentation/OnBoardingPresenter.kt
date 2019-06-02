package chat.rocket.luk.authentication.onboarding.presentation

import chat.rocket.luk.authentication.presentation.AuthenticationNavigator
import chat.rocket.luk.core.behaviours.showMessage
import chat.rocket.luk.core.lifecycle.CancelStrategy
import chat.rocket.luk.server.domain.GetAccountsInteractor
import chat.rocket.luk.server.domain.GetSettingsInteractor
import chat.rocket.luk.server.domain.RefreshSettingsInteractor
import chat.rocket.luk.server.domain.SaveConnectingServerInteractor
import chat.rocket.luk.server.infrastructure.RocketChatClientFactory
import chat.rocket.luk.server.presentation.CheckServerPresenter
import chat.rocket.luk.util.extension.launchUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class OnBoardingPresenter @Inject constructor(
    private val view: OnBoardingView,
    private val strategy: CancelStrategy,
    private val navigator: AuthenticationNavigator,
    private val serverInteractor: SaveConnectingServerInteractor,
    refreshSettingsInteractor: RefreshSettingsInteractor,
    private val getAccountsInteractor: GetAccountsInteractor,
    val settingsInteractor: GetSettingsInteractor,
    val factory: RocketChatClientFactory
) : CheckServerPresenter(
    strategy = strategy,
    factory = factory,
    settingsInteractor = settingsInteractor,
    refreshSettingsInteractor = refreshSettingsInteractor
) {

    fun toSignInToYourServer() = navigator.toSignInToYourServer()

    fun toCreateANewServer(createServerUrl: String) = navigator.toWebPage(createServerUrl)

    fun connectToCommunityServer(communityServerUrl: String) {
        connectToServer(communityServerUrl) {
            if (totalSocialAccountsEnabled == 0 && !isNewAccountCreationEnabled) {
                navigator.toLogin(communityServerUrl)
            } else {
                navigator.toLoginOptions(
                    communityServerUrl,
                    state,
                    facebookOauthUrl,
                    githubOauthUrl,
                    googleOauthUrl,
                    linkedinOauthUrl,
                    gitlabOauthUrl,
                    wordpressOauthUrl,
                    casLoginUrl,
                    casToken,
                    casServiceName,
                    casServiceNameTextColor,
                    casServiceButtonColor,
                    customOauthUrl,
                    customOauthServiceName,
                    customOauthServiceNameTextColor,
                    customOauthServiceButtonColor,
                    samlUrl,
                    samlToken,
                    samlServiceName,
                    samlServiceNameTextColor,
                    samlServiceButtonColor,
                    totalSocialAccountsEnabled,
                    isLoginFormEnabled,
                    isNewAccountCreationEnabled
                )
            }
        }
    }

    private fun connectToServer(serverUrl: String, block: () -> Unit) {
        launchUI(strategy) {
            // Check if we already have an account for this server...
            val account = getAccountsInteractor.get().firstOrNull { it.serverUrl == serverUrl }
            if (account != null) {
                navigator.toChatList(serverUrl)
                return@launchUI
            }
            view.showLoading()
            try {
                withContext(Dispatchers.Default) {
                    setupConnectionInfo(serverUrl)

                    // preparing next fragment before showing it
                    refreshServerAccounts()
                    checkEnabledAccounts(serverUrl)
                    checkIfLoginFormIsEnabled()
                    checkIfCreateNewAccountIsEnabled()

                    serverInteractor.save(serverUrl)

                    block()
                }
            } catch (ex: Exception) {
                view.showMessage(ex)
            } finally {
                view.hideLoading()
            }
        }
    }
}