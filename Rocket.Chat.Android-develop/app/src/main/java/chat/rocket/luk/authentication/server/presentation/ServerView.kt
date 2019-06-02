package chat.rocket.luk.authentication.server.presentation

import chat.rocket.luk.core.behaviours.LoadingView
import chat.rocket.luk.core.behaviours.MessageView

interface ServerView : LoadingView, MessageView, VersionCheckView {

    /**
     * Shows an invalid server URL message.
     */
    fun showInvalidServerUrlMessage()

    /**
     * Enables the button to connect to the server when the user inputs a valid url.
     */
    fun enableButtonConnect()

    /**
     * Disables the button to connect to the server when the server address entered by the user
     * is not a valid url.
     */
    fun disableButtonConnect()
}