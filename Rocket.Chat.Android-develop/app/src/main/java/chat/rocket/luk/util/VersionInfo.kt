package chat.rocket.luk.util

data class VersionInfo(
        val major: Int,
        val minor: Int,
        val update: Int = 0,
        val release: String?,
        val full: String
)