package chat.rocket.luk.authentication.twofactor.presentation

import chat.rocket.luk.core.behaviours.LoadingView
import chat.rocket.luk.core.behaviours.MessageView

interface TwoFAView : LoadingView, MessageView {

    /**
     * Enables the button to set the username if the user entered at least one character.
     */
    fun enableButtonConfirm()

    /**
     * Disables the button to set the username when there is no character entered by the user.
     */
    fun disableButtonConfirm()

    /**
     * Alerts the user about an invalid inputted Two Factor Authentication code.
     */
    fun alertInvalidTwoFactorAuthenticationCode()
}