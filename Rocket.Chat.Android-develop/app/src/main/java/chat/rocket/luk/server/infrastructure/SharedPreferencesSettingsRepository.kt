package chat.rocket.luk.server.infrastructure

import chat.rocket.luk.infrastructure.LocalRepository
import chat.rocket.luk.infrastructure.LocalRepository.Companion.SETTINGS_KEY
import chat.rocket.luk.server.domain.PublicSettings
import chat.rocket.luk.server.domain.SettingsRepository
import chat.rocket.core.internal.SettingsAdapter
import timber.log.Timber

class SharedPreferencesSettingsRepository(
    private val localRepository: LocalRepository
) : SettingsRepository {

    private val adapter = SettingsAdapter().lenient()

    override fun save(url: String, settings: PublicSettings) {
        if (settings.isEmpty()) {
            val message = "Saving empty settings for $SETTINGS_KEY$url"
            Timber.d(IllegalStateException(message), message)
        }
        localRepository.save("$SETTINGS_KEY$url", adapter.toJson(settings))
    }

    override fun get(url: String): PublicSettings {
        val settingsStr = localRepository.get("$SETTINGS_KEY$url")
        return if (settingsStr == null) {
            val message = "NULL Settings for: $SETTINGS_KEY$url"
            Timber.d(IllegalStateException(message), message)
            hashMapOf()
        } else {
            val settings = adapter.fromJson(settingsStr)

            if (settings == null) {
                val message = "NULL Settings for: $SETTINGS_KEY$url with saved settings: $settingsStr"
                Timber.d(IllegalStateException(message), message)
            }
            settings ?: hashMapOf()
        }
    }
}