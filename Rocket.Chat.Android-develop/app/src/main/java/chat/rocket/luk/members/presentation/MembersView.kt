package chat.rocket.luk.members.presentation

import chat.rocket.luk.core.behaviours.LoadingView
import chat.rocket.luk.core.behaviours.MessageView
import chat.rocket.luk.members.uimodel.MemberUiModel

interface MembersView: LoadingView, MessageView {

    /**
     * Shows a list of members of a room.
     *
     * @param dataSet The data set to show.
     * @param total The total number of members.
     */
    fun showMembers(dataSet: List<MemberUiModel>, total: Long)
}