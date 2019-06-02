package chat.rocket.luk.main.presentation

import chat.rocket.luk.core.behaviours.AppLanguageView
import chat.rocket.luk.push.GroupedPush
import chat.rocket.luk.server.domain.GetCurrentLanguageInteractor
import chat.rocket.luk.server.domain.RefreshPermissionsInteractor
import chat.rocket.luk.server.domain.RefreshSettingsInteractor
import chat.rocket.luk.server.infrastructure.ConnectionManagerFactory
import javax.inject.Inject
import javax.inject.Named

class MainPresenter @Inject constructor(
    @Named("currentServer") private val currentServerUrl: String,
    private val mainNavigator: MainNavigator,
    private val appLanguageView: AppLanguageView,
    private val refreshSettingsInteractor: RefreshSettingsInteractor,
    private val refreshPermissionsInteractor: RefreshPermissionsInteractor,
    private val connectionManagerFactory: ConnectionManagerFactory,
    private var getLanguageInteractor: GetCurrentLanguageInteractor,
    private val groupedPush: GroupedPush
) {

    fun connect() {
        refreshSettingsInteractor.refreshAsync(currentServerUrl)
        refreshPermissionsInteractor.refreshAsync(currentServerUrl)
        connectionManagerFactory.create(currentServerUrl).connect()
    }

    fun clearNotificationsForChatRoom(chatRoomId: String?) {
        if (chatRoomId == null) return

        groupedPush.hostToPushMessageList[currentServerUrl].let { list ->
            list?.removeAll { it.info.roomId == chatRoomId }
        }
    }

    fun showChatList(chatRoomId: String? = null) = mainNavigator.toChatList(chatRoomId)


    fun getAppLanguage() {
        with(getLanguageInteractor) {
            getLanguage()?.let { language ->
                appLanguageView.updateLanguage(language, getCountry())
            }
        }
    }
}