package chat.rocket.luk.chatroom.uimodel.suggestion

import chat.rocket.luk.suggestions.model.SuggestionModel

class CommandSuggestionUiModel(
    text: String,
    val description: String,
    searchList: List<String>
) : SuggestionModel(text, searchList)