package chat.rocket.luk.chatroom.uimodel

interface BaseAttachmentUiModel<out T> : BaseUiModel<T> {
    val attachmentUrl: String
}