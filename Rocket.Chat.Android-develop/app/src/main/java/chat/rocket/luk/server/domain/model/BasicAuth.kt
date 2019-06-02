package chat.rocket.luk.server.domain.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class BasicAuth(
    val host: String,
    val credentials: String
)
