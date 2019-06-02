package chat.rocket.luk.settings.presentation

import chat.rocket.luk.core.behaviours.LoadingView
import chat.rocket.luk.core.behaviours.MessageView
import chat.rocket.luk.server.presentation.TokenView

interface SettingsView : TokenView, LoadingView, MessageView {

    /**
     * Setups the settings view.
     *
     * @param avatar The user avatar.
     * @param displayName The user display name.
     * @param status The user status.
     * @param isAdministrationEnabled True if the administration is enabled, false otherwise.
     * @param isAnalyticsTrackingEnabled True if the analytics tracking is enabled, false otherwise.
     * @param isDeleteAccountEnabled True if the delete account is enabled, false otherwise.
     * @param serverVersion The version of the current logged in server.
     */
    fun setupSettingsView(
        avatar: String,
        displayName: String,
        status: String,
        isAdministrationEnabled: Boolean,
        isAnalyticsTrackingEnabled: Boolean,
        isDeleteAccountEnabled: Boolean,
        serverVersion: String
    )
}
