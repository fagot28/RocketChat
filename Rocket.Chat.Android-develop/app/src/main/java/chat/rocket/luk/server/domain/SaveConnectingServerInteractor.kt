package chat.rocket.luk.server.domain

import chat.rocket.luk.dagger.qualifier.ForAuthentication
import javax.inject.Inject

class SaveConnectingServerInteractor @Inject constructor(
    @ForAuthentication private val repository: CurrentServerRepository
) {
    fun save(url: String) = repository.save(url)
}