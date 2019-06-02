package chat.rocket.luk.server.presentation

import chat.rocket.luk.analytics.AnalyticsManager
import chat.rocket.luk.core.lifecycle.CancelStrategy
import chat.rocket.luk.infrastructure.LocalRepository
import chat.rocket.luk.server.domain.GetAccountInteractor
import chat.rocket.luk.server.domain.GetAccountsInteractor
import chat.rocket.luk.server.domain.GetCurrentServerInteractor
import chat.rocket.luk.server.domain.SaveCurrentServerInteractor
import chat.rocket.luk.server.domain.SettingsRepository
import chat.rocket.luk.server.domain.TokenRepository
import chat.rocket.luk.server.infrastructure.ConnectionManagerFactory
import chat.rocket.luk.util.extension.launchUI
import chat.rocket.common.util.ifNull
import javax.inject.Inject

class ChangeServerPresenter @Inject constructor(
    private val view: ChangeServerView,
    private val navigator: ChangeServerNavigator,
    private val strategy: CancelStrategy,
    private val saveCurrentServerInteractor: SaveCurrentServerInteractor,
    private val getCurrentServerInteractor: GetCurrentServerInteractor,
    private val getAccountInteractor: GetAccountInteractor,
    private val getAccountsInteractor: GetAccountsInteractor,
    private val analyticsManager: AnalyticsManager,
    private val settingsRepository: SettingsRepository,
    private val tokenRepository: TokenRepository,
    private val localRepository: LocalRepository,
    private val connectionManager: ConnectionManagerFactory
) {

    fun loadServer(newUrl: String?, chatRoomId: String? = null) {
        launchUI(strategy) {
            view.showProgress()
            var url = newUrl
            val accounts = getAccountsInteractor.get()
            if (url == null) {
                // Try to load next server on the list...
                url = accounts.firstOrNull()?.serverUrl
            }

            url?.let { serverUrl ->
                val token = tokenRepository.get(serverUrl)
                if (token == null) {
                    view.showInvalidCredentials()
                    view.hideProgress()
                    navigator.toServerScreen()
                    return@launchUI
                }

                val settings = settingsRepository.get(serverUrl)
                if (settings == null) {
                    // TODO - reload settings...
                }

                // Call disconnect on the old url if any...
                getCurrentServerInteractor.get()?.let { url ->
                    connectionManager.get(url)?.disconnect()
                }

                // Save the current username.
                getAccountInteractor.get(serverUrl)?.let { account ->
                    localRepository.save(LocalRepository.CURRENT_USERNAME_KEY, account.userName)
                }

                saveCurrentServerInteractor.save(serverUrl)
                view.hideProgress()
                analyticsManager.logServerSwitch()
                navigator.toChatRooms(chatRoomId)
            }.ifNull {
                view.hideProgress()
                navigator.toServerScreen()
            }
        }
    }
}