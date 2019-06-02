package chat.rocket.luk.members.uimodel

import chat.rocket.luk.server.domain.GetCurrentServerInteractor
import chat.rocket.luk.server.domain.GetSettingsInteractor
import chat.rocket.luk.server.domain.TokenRepository
import chat.rocket.luk.server.domain.baseUrl
import chat.rocket.common.model.User
import chat.rocket.core.model.Value
import javax.inject.Inject
import javax.inject.Named

class MemberUiModelMapper @Inject constructor(
    serverInteractor: GetCurrentServerInteractor,
    getSettingsInteractor: GetSettingsInteractor,
    @Named("currentServer") private val currentServer: String,
    tokenRepository: TokenRepository
) {
    private var settings: Map<String, Value<Any>> = getSettingsInteractor.get(serverInteractor.get()!!)
    private val baseUrl = settings.baseUrl()
    private val token = tokenRepository.get(currentServer)

    fun mapToUiModelList(memberList: List<User>): List<MemberUiModel> {
        return memberList.map { MemberUiModel(it, settings, baseUrl, token) }
    }
}
