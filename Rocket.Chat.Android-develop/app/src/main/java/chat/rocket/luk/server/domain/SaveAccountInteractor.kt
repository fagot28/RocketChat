package chat.rocket.luk.server.domain

import chat.rocket.luk.server.domain.model.Account
import javax.inject.Inject

class SaveAccountInteractor @Inject constructor(val repository: AccountsRepository) {
    fun save(account: Account) = repository.save(account)
}