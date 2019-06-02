package chat.rocket.luk.members.presentation

import chat.rocket.luk.chatroom.presentation.ChatRoomNavigator
import chat.rocket.luk.core.lifecycle.CancelStrategy
import chat.rocket.luk.db.DatabaseManager
import chat.rocket.luk.helper.UserHelper
import chat.rocket.luk.members.uimodel.MemberUiModel
import chat.rocket.luk.members.uimodel.MemberUiModelMapper
import chat.rocket.luk.server.infrastructure.RocketChatClientFactory
import chat.rocket.luk.util.extension.launchUI
import chat.rocket.common.RocketChatException
import chat.rocket.common.model.roomTypeOf
import chat.rocket.common.util.ifNull
import chat.rocket.core.RocketChatClient
import chat.rocket.core.internal.rest.getMembers
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class MembersPresenter @Inject constructor(
    private val view: MembersView,
    private val navigator: ChatRoomNavigator,
    private val dbManager: DatabaseManager,
    @Named("currentServer") private val currentServer: String,
    private val strategy: CancelStrategy,
    private val mapper: MemberUiModelMapper,
    val factory: RocketChatClientFactory,
    private val userHelper: UserHelper
) {
    private val client: RocketChatClient = factory.get(currentServer)
    private var offset: Long = 0

    /**
     * Loads all the chat room members for the given room id.
     *
     * @param roomId The id of the room to get chat room members from.
     */
    fun loadChatRoomsMembers(roomId: String) {
        launchUI(strategy) {
            try {
                view.showLoading()
                dbManager.getRoom(roomId)?.let {
                    val members =
                        client.getMembers(roomId, roomTypeOf(it.chatRoom.type), offset, 60)
                    val memberUiModels = mapper.mapToUiModelList(members.result)
                    view.showMembers(memberUiModels, members.total)
                    offset += 1 * 60L
                }.ifNull {
                    Timber.e("Couldn't find a room with id: $roomId at current server.")
                }
            } catch (exception: RocketChatException) {
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

    fun toMemberDetails(memberUiModel: MemberUiModel) {
        with(memberUiModel) {
            if (userId != userHelper.user()?.id) {
                navigator.toMemberDetails(userId)
            }
        }
    }
}
