package chat.rocket.luk.server.presentation

interface ChangeServerView {
    fun showInvalidCredentials()
    fun showProgress()
    fun hideProgress()
}