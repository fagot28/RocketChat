package chat.rocket.luk.chatroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import chat.rocket.luk.R
import chat.rocket.luk.chatroom.adapter.RoomSuggestionsAdapter.RoomSuggestionsViewHolder
import chat.rocket.luk.chatroom.uimodel.suggestion.ChatRoomSuggestionUiModel
import chat.rocket.luk.suggestions.model.SuggestionModel
import chat.rocket.luk.suggestions.ui.BaseSuggestionViewHolder
import chat.rocket.luk.suggestions.ui.SuggestionsAdapter

class RoomSuggestionsAdapter : SuggestionsAdapter<RoomSuggestionsViewHolder>("#") {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomSuggestionsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.suggestion_room_item, parent,
                false)
        return RoomSuggestionsViewHolder(view)
    }

    class RoomSuggestionsViewHolder(view: View) : BaseSuggestionViewHolder(view) {

        override fun bind(item: SuggestionModel, itemClickListener: SuggestionsAdapter.ItemClickListener?) {
            item as ChatRoomSuggestionUiModel
            with(itemView) {
                val fullname = findViewById<TextView>(R.id.text_fullname)
                val name = findViewById<TextView>(R.id.text_name)
                name.text = item.name
                fullname.text = item.fullName
                setOnClickListener {
                    itemClickListener?.onClick(item)
                }
            }
        }
    }
}