package chat.rocket.luk.server.domain

import chat.rocket.luk.authentication.domain.model.TokenModel

interface MultiServerTokenRepository {
    fun get(server: String): TokenModel?

    fun save(server: String, token: TokenModel)

    fun clear(server: String)
}