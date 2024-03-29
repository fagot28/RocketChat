package chat.rocket.luk.profile.presentation

import chat.rocket.luk.core.behaviours.LoadingView
import chat.rocket.luk.core.behaviours.MessageView
import chat.rocket.luk.server.presentation.TokenView

interface ProfileView : TokenView, LoadingView, MessageView {

    /**
     * Shows the user profile.
     *
     * @param status The user status.
     * @param avatarUrl The user avatar URL.
     * @param name The user display name.
     * @param username The user username.
     * @param email The user email.
     */
    fun showProfile(
        status: String,
        avatarUrl: String,
        name: String,
        username: String,
        email: String?
    )

    /**
     * Reloads the user avatar (after successfully updating it).
     *
     * @param avatarUrl The user avatar URL.
     */
    fun reloadUserAvatar(avatarUrl: String)

    /**
     * Shows a profile update successfully message
     */
    fun showProfileUpdateSuccessfullyMessage()
}