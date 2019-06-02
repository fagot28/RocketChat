package chat.rocket.luk.pinnedmessages.presentation

import chat.rocket.luk.chatroom.uimodel.BaseUiModel
import chat.rocket.luk.core.behaviours.LoadingView
import chat.rocket.luk.core.behaviours.MessageView

interface PinnedMessagesView : MessageView, LoadingView {

    /**
     * Show list of pinned messages for the current room.
     *
     * @param pinnedMessages The list of pinned messages.
     */
    fun showPinnedMessages(pinnedMessages: List<BaseUiModel<*>>)
}