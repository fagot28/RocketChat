package chat.rocket.luk.server.infrastructure

import android.os.Build
import chat.rocket.luk.BuildConfig
import chat.rocket.luk.server.domain.TokenRepository
import chat.rocket.common.util.PlatformLogger
import chat.rocket.core.RocketChatClient
import okhttp3.OkHttpClient
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RocketChatClientFactory @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val repository: TokenRepository,
    private val logger: PlatformLogger
) {
    private val cache = HashMap<String, RocketChatClient>()

    fun get(url: String): RocketChatClient {
        cache[url]?.let {
            Timber.d("Returning CACHED client for: $url")
            return it
        }

        val client = RocketChatClient.create {
            httpClient = okHttpClient
            restUrl = url
            userAgent = "RC Mobile; Android ${Build.VERSION.RELEASE}; v${BuildConfig.VERSION_NAME} (${BuildConfig.VERSION_CODE})"
            tokenRepository = repository
            platformLogger = logger
            enableLogger = false
        }

        Timber.d("Returning NEW client for: $url")
        cache[url] = client
        return client
    }
}