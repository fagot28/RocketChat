package chat.rocket.luk.server.domain

interface TokenRepository : chat.rocket.core.TokenRepository {
    fun remove(url: String)
    fun clear()
}