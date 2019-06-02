package chat.rocket.luk.mentions.presentention

import chat.rocket.luk.chatroom.uimodel.BaseUiModel
import chat.rocket.luk.core.behaviours.LoadingView
import chat.rocket.luk.core.behaviours.MessageView

interface MentionsView : MessageView, LoadingView {

    /**
     * Shows the list of mentions for the current room.
     *
     * @param mentions The list of mentions.
     */
    fun showMentions(mentions: List<BaseUiModel<*>>)
}