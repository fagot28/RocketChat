package chat.rocket.luk.createchannel.presentation

import chat.rocket.luk.core.lifecycle.CancelStrategy
import chat.rocket.luk.main.presentation.MainNavigator
import chat.rocket.luk.members.uimodel.MemberUiModelMapper
import chat.rocket.luk.server.domain.GetCurrentServerInteractor
import chat.rocket.luk.server.infrastructure.RocketChatClientFactory
import chat.rocket.luk.util.extension.launchUI
import chat.rocket.common.RocketChatException
import chat.rocket.common.model.RoomType
import chat.rocket.common.util.ifNull
import chat.rocket.core.RocketChatClient
import chat.rocket.core.internal.rest.createChannel
import chat.rocket.core.internal.rest.spotlight
import javax.inject.Inject

class CreateChannelPresenter @Inject constructor(
    private val view: CreateChannelView,
    private val strategy: CancelStrategy,
    private val mapper: MemberUiModelMapper,
    private val navigator: MainNavigator,
    val serverInteractor: GetCurrentServerInteractor,
    val factory: RocketChatClientFactory
) {
    private val client: RocketChatClient = factory.get(serverInteractor.get()!!)

    fun createChannel(
        roomType: RoomType,
        channelName: String,
        usersList: List<String>,
        readOnly: Boolean
    ) {
        launchUI(strategy) {
            view.showLoading()
            view.disableUserInput()
            try {
                client.createChannel(roomType, channelName, usersList, readOnly)
                view.showChannelCreatedSuccessfullyMessage()
                toChatList()
            } catch (exception: RocketChatException) {
                exception.message?.let {
                    view.showMessage(it)
                }.ifNull {
                    view.showGenericErrorMessage()
                }
            } finally {
                view.hideLoading()
                view.enableUserInput()
            }
        }
    }

    fun searchUser(query: String) {
        launchUI(strategy) {
            view.showSuggestionViewInProgress()
            try {
                val users = client.spotlight(query).users
                if (users.isEmpty()) {
                    view.showNoUserSuggestion()
                } else {
                    view.showUserSuggestion(mapper.mapToUiModelList(users))
                }
            } catch (ex: RocketChatException) {
                ex.message?.let {
                    view.showMessage(it)
                }.ifNull {
                    view.showGenericErrorMessage()
                }
            } finally {
                view.hideSuggestionViewInProgress()
            }
        }
    }

    fun toChatList() = navigator.toChatList()
}