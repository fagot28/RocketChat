package chat.rocket.luk.settings.password.presentation

import chat.rocket.luk.analytics.AnalyticsManager
import chat.rocket.luk.core.lifecycle.CancelStrategy
import chat.rocket.luk.helper.UserHelper
import chat.rocket.luk.server.domain.GetCurrentServerInteractor
import chat.rocket.luk.server.infrastructure.RocketChatClientFactory
import chat.rocket.luk.util.extension.launchUI
import chat.rocket.luk.util.retryIO
import chat.rocket.common.RocketChatException
import chat.rocket.core.RocketChatClient
import chat.rocket.core.internal.rest.updateProfile
import javax.inject.Inject

class PasswordPresenter @Inject constructor(
    private val view: PasswordView,
    private val strategy: CancelStrategy,
    private val analyticsManager: AnalyticsManager,
    private val userHelp: UserHelper,
    serverInteractor: GetCurrentServerInteractor,
    factory: RocketChatClientFactory
) {
    private val serverUrl = serverInteractor.get()!!
    private val client: RocketChatClient = factory.get(serverUrl)

    fun updatePassword(password: String) {
        launchUI(strategy) {
            try {
                view.showLoading()
                userHelp.user()?.id?.let { userId ->
                    retryIO("updateProfile()") {
                        client.updateProfile(userId, null, null, password, null)
                    }
                    analyticsManager.logResetPassword(true)
                    view.showPasswordSuccessfullyUpdatedMessage()
                }
            } catch (exception: RocketChatException) {
                analyticsManager.logResetPassword(false)
                exception.message?.let { errorMessage ->
                    view.showPasswordFailsUpdateMessage(errorMessage)
                }
            } finally {
                view.hideLoading()
            }
        }
    }
}