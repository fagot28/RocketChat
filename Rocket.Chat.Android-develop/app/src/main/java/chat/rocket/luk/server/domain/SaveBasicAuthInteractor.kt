package chat.rocket.luk.server.domain

import chat.rocket.luk.server.domain.model.BasicAuth
import javax.inject.Inject

class SaveBasicAuthInteractor @Inject constructor(val repository: BasicAuthRepository) {
    fun save(basicAuth: BasicAuth) = repository.save(basicAuth)
}
