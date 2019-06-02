package chat.rocket.luk.servers.presentation

import chat.rocket.luk.core.behaviours.MessageView
import chat.rocket.luk.server.domain.model.Account

interface ServersView : MessageView {

    /**
     * Shows the server list.
     *
     * @param serverList The list of server to show.
     * @param currentServerUrl The current logged in server url.
     */
    fun showServerList(serverList: List<Account>, currentServerUrl: String)

    /**
     * Hides the servers view.
     */
    fun hideServerView()
}