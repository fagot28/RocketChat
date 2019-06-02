package chat.rocket.luk.createchannel.presentation

import chat.rocket.luk.core.behaviours.LoadingView
import chat.rocket.luk.core.behaviours.MessageView
import chat.rocket.luk.members.uimodel.MemberUiModel

interface CreateChannelView : LoadingView, MessageView {

    /**
     * Shows the server's users suggestion (on the basis of the user typing - the query).
     *
     * @param dataSet The list of server's users to show.
     */
    fun showUserSuggestion(dataSet: List<MemberUiModel>)

    /**
     * Shows no server's users suggestion.
     */
    fun showNoUserSuggestion()

    /**
     * Shows the SuggestionView in progress.
     */
    fun showSuggestionViewInProgress()

    /**
     * Hides the progress shown in the SuggestionView.
     */
    fun hideSuggestionViewInProgress()

    /**
     * Shows a message that a channel was successfully created.
     */
    fun showChannelCreatedSuccessfullyMessage()

    /**
     * Enables the user input.
     */
    fun enableUserInput()

    /**
     * Disables the user input.
     */
    fun disableUserInput()
}