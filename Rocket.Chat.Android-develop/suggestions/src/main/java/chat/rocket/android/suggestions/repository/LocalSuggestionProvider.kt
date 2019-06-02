package chat.rocket.luk.suggestions.repository

interface LocalSuggestionProvider {
    fun find(prefix: String)
}