package chat.rocket.luk.server.domain

import chat.rocket.luk.server.domain.model.Account

interface AccountsRepository {

    fun save(account: Account)
    fun load(): List<Account>
    fun remove(serverUrl: String)
}