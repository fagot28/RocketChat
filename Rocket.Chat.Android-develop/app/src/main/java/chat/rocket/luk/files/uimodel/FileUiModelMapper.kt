package chat.rocket.luk.files.uimodel

import chat.rocket.luk.server.domain.GetCurrentServerInteractor
import chat.rocket.luk.server.domain.GetSettingsInteractor
import chat.rocket.luk.server.domain.TokenRepository
import chat.rocket.luk.server.domain.baseUrl
import chat.rocket.core.model.Value
import chat.rocket.core.model.attachment.GenericAttachment
import javax.inject.Inject

class FileUiModelMapper @Inject constructor(
    serverInteractor: GetCurrentServerInteractor,
    getSettingsInteractor: GetSettingsInteractor,
    private val tokenRepository: TokenRepository
) {
    private var settings: Map<String, Value<Any>> =
        getSettingsInteractor.get(serverInteractor.get()!!)
    private val baseUrl = settings.baseUrl()

    fun mapToUiModelList(fileList: List<GenericAttachment>): List<FileUiModel> {
        return fileList.map { FileUiModel(it, settings, tokenRepository, baseUrl) }
    }
}