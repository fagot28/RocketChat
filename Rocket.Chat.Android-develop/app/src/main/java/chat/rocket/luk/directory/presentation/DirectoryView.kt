package chat.rocket.luk.directory.presentation

import chat.rocket.luk.core.behaviours.LoadingView
import chat.rocket.luk.core.behaviours.MessageView
import chat.rocket.luk.directory.uimodel.DirectoryUiModel

interface DirectoryView : MessageView, LoadingView {

    /**
     * Shows the list of directory channels.
     *
     * @param dataSet The data set to show.
     */
    fun showChannels(dataSet: List<DirectoryUiModel>)

    /**
     * Shows the list of directory users.
     *
     * @param dataSet The data set to show.
     */
    fun showUsers(dataSet: List<DirectoryUiModel>)
}