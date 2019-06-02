package chat.rocket.luk.server.domain

import chat.rocket.luk.server.infrastructure.CurrentLanguageRepository
import javax.inject.Inject

class GetCurrentLanguageInteractor @Inject constructor(
    private val repository: CurrentLanguageRepository
) {

    fun getLanguage(): String? = repository.getLanguage()
    fun getCountry(): String? = repository.getCountry()
}