package chat.rocket.luk.chatroom.uimodel.suggestion

import chat.rocket.luk.suggestions.model.SuggestionModel

class ChatRoomSuggestionUiModel(
    text: String,
    val fullName: String,
    val name: String,
    searchList: List<String>
) : SuggestionModel(text, searchList, false)
