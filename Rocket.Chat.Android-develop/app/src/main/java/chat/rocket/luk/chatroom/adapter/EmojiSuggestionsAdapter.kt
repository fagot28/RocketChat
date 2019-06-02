package chat.rocket.luk.chatroom.adapter

import android.annotation.SuppressLint
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import chat.rocket.luk.R
import chat.rocket.luk.chatroom.adapter.EmojiSuggestionsAdapter.EmojiSuggestionViewHolder
import chat.rocket.luk.chatroom.uimodel.suggestion.EmojiSuggestionUiModel
import chat.rocket.luk.emoji.EmojiParser
import chat.rocket.luk.emoji.internal.isCustom
import chat.rocket.luk.suggestions.model.SuggestionModel
import chat.rocket.luk.suggestions.strategy.trie.TrieCompletionStrategy
import chat.rocket.luk.suggestions.ui.BaseSuggestionViewHolder
import chat.rocket.luk.suggestions.ui.SuggestionsAdapter
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.suggestion_emoji_item.view.*

class EmojiSuggestionsAdapter : SuggestionsAdapter<EmojiSuggestionViewHolder>(
    token = ":",
    completionStrategy = TrieCompletionStrategy()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmojiSuggestionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.suggestion_emoji_item, parent,false)
        return EmojiSuggestionViewHolder(view)
    }

    class EmojiSuggestionViewHolder(view: View) : BaseSuggestionViewHolder(view) {

        @SuppressLint("SetTextI18n")
        override fun bind(item: SuggestionModel, itemClickListener: SuggestionsAdapter.ItemClickListener?) {
            item as EmojiSuggestionUiModel
            with(itemView) {
                text_emoji_shortname.text = ":${item.text}"
                if (item.emoji.isCustom()) {
                    view_flipper_emoji.displayedChild = 1
                    val sp = SpannableStringBuilder().append(item.emoji.shortname)
                    Glide.with(context).asDrawable().load(item.emoji.url).into(image_emoji)
                } else {
                    text_emoji.text = EmojiParser.parse(context, item.emoji.unicode)
                    view_flipper_emoji.displayedChild = 0
                }
                setOnClickListener {
                    itemClickListener?.onClick(item)
                }
            }
        }
    }
}
