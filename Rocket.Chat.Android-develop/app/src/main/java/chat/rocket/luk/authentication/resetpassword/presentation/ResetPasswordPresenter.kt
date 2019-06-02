package chat.rocket.luk.authentication.resetpassword.presentation

import chat.rocket.luk.authentication.presentation.AuthenticationNavigator
import chat.rocket.luk.core.lifecycle.CancelStrategy
import chat.rocket.luk.server.domain.GetConnectingServerInteractor
import chat.rocket.luk.server.infrastructure.RocketChatClientFactory
import chat.rocket.luk.util.extension.launchUI
import chat.rocket.luk.util.retryIO
import chat.rocket.common.RocketChatException
import chat.rocket.common.RocketChatInvalidResponseException
import chat.rocket.common.util.ifNull
import chat.rocket.core.RocketChatClient
import chat.rocket.core.internal.rest.forgotPassword
import javax.inject.Inject

class ResetPasswordPresenter @Inject constructor(
    private val view: ResetPasswordView,
    private val strategy: CancelStrategy,
    private val navigator: AuthenticationNavigator,
    factory: RocketChatClientFactory,
    serverInteractor: GetConnectingServerInteractor
) {
    private val currentServer = serverInteractor.get()!!
    private val client: RocketChatClient = factory.get(currentServer)

    fun resetPassword(email: String) {
        launchUI(strategy) {
            view.showLoading()
            try {
                retryIO("forgotPassword(email = $email)") {
                    client.forgotPassword(email)
                }
                navigator.toPreviousView()
                view.emailSent()
            } catch (exception: RocketChatException) {
                if (exception is RocketChatInvalidResponseException) {
                    view.updateYourServerVersion()
                } else {
                    exception.message?.let {
                        view.showMessage(it)
                    }.ifNull {
                        view.showGenericErrorMessage()
                    }
                }
            } finally {
                view.hideLoading()
            }
        }
    }
}