package chat.rocket.luk.chatroom.adapter

import android.view.View
import androidx.core.view.isVisible
import chat.rocket.luk.chatroom.uimodel.UrlPreviewUiModel
import chat.rocket.luk.emoji.EmojiReactionListener
import chat.rocket.luk.util.extensions.content
import chat.rocket.luk.util.extensions.openTabbedUrl
import kotlinx.android.synthetic.main.message_url_preview.view.*

class UrlPreviewViewHolder(
    itemView: View,
    listener: ActionsListener,
    reactionListener: EmojiReactionListener? = null
) : BaseViewHolder<UrlPreviewUiModel>(itemView, listener, reactionListener) {

    init {
        setupActionMenu(itemView.url_preview_layout)
    }

    override fun bindViews(data: UrlPreviewUiModel) {
        with(itemView) {
            if (data.thumbUrl.isNullOrEmpty()) {
                image_preview.isVisible = false
            } else {
                image_preview.setImageURI(data.thumbUrl)
                image_preview.isVisible = true
            }
            text_host.content = data.hostname
            text_title.content = data.title
            text_description.content = data.description ?: ""

            url_preview_layout.setOnClickListener(onClickListener)
            text_host.setOnClickListener(onClickListener)
            text_title.setOnClickListener(onClickListener)
            image_preview.setOnClickListener(onClickListener)
            text_description.setOnClickListener(onClickListener)
        }
    }

    private val onClickListener = { view: View ->
        if (data != null) {
            view.openTabbedUrl(data!!.rawData.url)
        }
    }
}