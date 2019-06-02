package chat.rocket.luk.server.domain

import javax.inject.Inject

class RemoveAccountInteractor @Inject constructor(val repository: AccountsRepository) {

    fun remove(serverUrl: String) {
        repository.remove(serverUrl)
    }
}