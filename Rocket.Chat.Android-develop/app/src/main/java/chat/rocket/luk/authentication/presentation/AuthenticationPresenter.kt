package chat.rocket.luk.authentication.presentation

import chat.rocket.luk.core.lifecycle.CancelStrategy
import chat.rocket.luk.infrastructure.LocalRepository
import chat.rocket.luk.server.domain.GetAccountInteractor
import chat.rocket.luk.server.domain.GetConnectingServerInteractor
import chat.rocket.luk.server.domain.GetCurrentServerInteractor
import chat.rocket.luk.server.domain.SettingsRepository
import chat.rocket.luk.server.domain.TokenRepository
import chat.rocket.luk.util.extension.launchUI
import chat.rocket.luk.util.extensions.privacyPolicyUrl
import chat.rocket.luk.util.extensions.termsOfServiceUrl
import javax.inject.Inject

class AuthenticationPresenter @Inject constructor(
    private val strategy: CancelStrategy,
    private val navigator: AuthenticationNavigator,
    private val getCurrentServerInteractor: GetCurrentServerInteractor,
    private val getAccountInteractor: GetAccountInteractor,
    private val settingsRepository: SettingsRepository,
    private val localRepository: LocalRepository,
    private val tokenRepository: TokenRepository,
    private val serverInteractor: GetConnectingServerInteractor
) {

    fun loadCredentials(newServer: Boolean, callback: (isAuthenticated: Boolean) -> Unit) {
        launchUI(strategy) {
            val currentServer = getCurrentServerInteractor.get()
            val serverToken = currentServer?.let { tokenRepository.get(currentServer) }
            val settings = currentServer?.let { settingsRepository.get(currentServer) }
            val account = currentServer?.let { getAccountInteractor.get(currentServer) }

            account?.let {
                localRepository.save(LocalRepository.CURRENT_USERNAME_KEY, account.userName)
            }

            if (newServer || currentServer == null ||
                serverToken == null ||
                settings == null ||
                account?.userName == null
            ) {
                callback(false)
            } else {
                callback(true)
            }
        }
    }

    fun termsOfService(toolbarTitle: String) =
        serverInteractor.get()?.let { navigator.toWebPage(it.termsOfServiceUrl(), toolbarTitle) }

    fun privacyPolicy(toolbarTitle: String) =
        serverInteractor.get()?.let { navigator.toWebPage(it.privacyPolicyUrl(), toolbarTitle) }

    fun toChatList() = navigator.toChatList()
}