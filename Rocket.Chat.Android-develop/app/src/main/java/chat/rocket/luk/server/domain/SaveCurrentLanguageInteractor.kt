package chat.rocket.luk.server.domain

import chat.rocket.luk.server.infrastructure.CurrentLanguageRepository
import javax.inject.Inject

class SaveCurrentLanguageInteractor @Inject constructor(
    private val repository: CurrentLanguageRepository
) {
    fun save(language: String, country: String?) = repository.save(language, country)
}