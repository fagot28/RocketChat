package chat.rocket.luk.server.domain

import chat.rocket.luk.dagger.qualifier.ForAuthentication
import javax.inject.Inject

class GetConnectingServerInteractor @Inject constructor(
    @ForAuthentication private val repository: CurrentServerRepository
) {
    fun get(): String? = repository.get()

    fun clear() {
        repository.clear()
    }
}