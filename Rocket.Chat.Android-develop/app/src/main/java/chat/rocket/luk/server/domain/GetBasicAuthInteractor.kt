package chat.rocket.luk.server.domain

import javax.inject.Inject

class GetBasicAuthInteractor @Inject constructor(val repository: BasicAuthRepository) {
    fun getAll() = repository.load().listIterator()
}
