package chat.rocket.luk.chatroom.uimodel.suggestion

import chat.rocket.luk.emoji.Emoji
import chat.rocket.luk.suggestions.model.SuggestionModel

class EmojiSuggestionUiModel(
    text: String,
    pinned: Boolean = false,
    val emoji: Emoji,
    searchList: List<String>
) : SuggestionModel(text, searchList, pinned) {

    override fun toString(): String {
        return "EmojiSuggestionUiModel(text='$text', searchList='$searchList', pinned=$pinned)"
    }
}
