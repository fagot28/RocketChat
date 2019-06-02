package chat.rocket.luk.mentions.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import chat.rocket.luk.R
import chat.rocket.luk.analytics.AnalyticsManager
import chat.rocket.luk.analytics.event.ScreenViewEvent
import chat.rocket.luk.chatroom.adapter.ChatRoomAdapter
import chat.rocket.luk.chatroom.ui.ChatRoomActivity
import chat.rocket.luk.chatroom.uimodel.BaseUiModel
import chat.rocket.luk.helper.EndlessRecyclerViewScrollListener
import chat.rocket.luk.mentions.presentention.MentionsPresenter
import chat.rocket.luk.mentions.presentention.MentionsView
import chat.rocket.luk.util.extensions.inflate
import chat.rocket.luk.util.extensions.showToast
import chat.rocket.luk.util.extensions.ui
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_mentions.*
import javax.inject.Inject

fun newInstance(chatRoomId: String): Fragment = MentionsFragment().apply {
    arguments = Bundle(1).apply {
        putString(BUNDLE_CHAT_ROOM_ID, chatRoomId)
    }
}

internal const val TAG_MENTIONS_FRAGMENT = "MentionsFragment"
private const val BUNDLE_CHAT_ROOM_ID = "chat_room_id"

class MentionsFragment : Fragment(), MentionsView {
    @Inject
    lateinit var presenter: MentionsPresenter
    @Inject
    lateinit var analyticsManager: AnalyticsManager
    private lateinit var chatRoomId: String
    private val adapter = ChatRoomAdapter(enableActions = false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

        arguments?.run {
            chatRoomId = getString(BUNDLE_CHAT_ROOM_ID, "")
        } ?: requireNotNull(arguments) { "no arguments supplied when the fragment was instantiated" }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = container?.inflate(R.layout.fragment_mentions)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        presenter.loadMentions(chatRoomId)

        analyticsManager.logScreenView(ScreenViewEvent.Mentions)
    }

    override fun showMentions(mentions: List<BaseUiModel<*>>) {
        ui {
            if (recycler_view.adapter == null) {
                recycler_view.adapter = adapter

                val linearLayoutManager = LinearLayoutManager(context)
                recycler_view.layoutManager = linearLayoutManager
                recycler_view.itemAnimator = DefaultItemAnimator()
                if (mentions.size >= 30) {
                    recycler_view.addOnScrollListener(object :
                        EndlessRecyclerViewScrollListener(linearLayoutManager) {
                        override fun onLoadMore(
                            page: Int,
                            totalItemsCount: Int,
                            recyclerView: RecyclerView
                        ) {
                            presenter.loadMentions(chatRoomId)
                        }

                    })
                }
                group_no_mention.isVisible = mentions.isEmpty()
            }
            adapter.appendData(mentions)
        }
    }

    override fun showMessage(resId: Int) {
        ui {
            showToast(resId)
        }
    }

    override fun showMessage(message: String) {
        ui {
            showToast(message)
        }
    }

    override fun showGenericErrorMessage() = showMessage(getString(R.string.msg_generic_error))

    override fun showLoading() {
        ui { view_loading.isVisible = true }
    }

    override fun hideLoading() {
        ui { view_loading.isVisible = false }
    }

    private fun setupToolbar() {
        (activity as ChatRoomActivity).setupToolbarTitle((getString(R.string.msg_mentions)))
    }
}