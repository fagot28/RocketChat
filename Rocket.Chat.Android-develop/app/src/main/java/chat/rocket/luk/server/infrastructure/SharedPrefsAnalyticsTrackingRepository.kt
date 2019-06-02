package chat.rocket.luk.server.infrastructure

import android.content.SharedPreferences
import chat.rocket.luk.server.domain.AnalyticsTrackingRepository

private const val ANALYTICS_TRACKING_KEY = "ANALYTICS_TRACKING_KEY"

class SharedPrefsAnalyticsTrackingRepository(private val preferences: SharedPreferences) :
    AnalyticsTrackingRepository {

    override fun save(isAnalyticsTrackingEnable: Boolean) =
        preferences.edit().putBoolean(ANALYTICS_TRACKING_KEY, isAnalyticsTrackingEnable).apply()

    override fun get() = preferences.getBoolean(ANALYTICS_TRACKING_KEY, true)
}