package chat.rocket.luk.server.domain

interface AnalyticsTrackingRepository {
    fun save(isAnalyticsTrackingEnable: Boolean)
    fun get(): Boolean
}