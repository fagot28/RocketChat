package chat.rocket.luk.favoritemessages.presentation

import chat.rocket.luk.chatroom.uimodel.BaseUiModel
import chat.rocket.luk.core.behaviours.LoadingView
import chat.rocket.luk.core.behaviours.MessageView

interface FavoriteMessagesView : MessageView, LoadingView {

    /**
     * Shows the list of favorite messages for the current room.
     *
     * @param favoriteMessages The list of favorite messages to show.
     */
    fun showFavoriteMessages(favoriteMessages: List<BaseUiModel<*>>)
}