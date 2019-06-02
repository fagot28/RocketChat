package chat.rocket.luk.server.domain

import chat.rocket.luk.server.domain.model.BasicAuth

interface BasicAuthRepository {
    fun save(basicAuth: BasicAuth)
    fun load(): List<BasicAuth>
}
