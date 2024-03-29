package chat.rocket.luk.util.extensions

import chat.rocket.luk.db.model.MessageEntity
import chat.rocket.luk.server.domain.model.Account
import chat.rocket.luk.server.infrastructure.RocketChatClientFactory
import chat.rocket.luk.util.retryIO
import chat.rocket.core.internal.rest.registerPushToken
import chat.rocket.core.model.Message
import chat.rocket.core.model.asString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

suspend fun RocketChatClientFactory.registerPushToken(
    token: String,
    accounts: List<Account>
) {
    withContext(Dispatchers.IO) {
        accounts.forEach { account ->
            try {
                retryIO(description = "register push token: ${account.serverUrl}") {
                    get(account.serverUrl).registerPushToken(token)
                }
            } catch (ex: Exception) {
                Timber.d(ex, "Error registering Push token for ${account.serverUrl}")
                ex.printStackTrace()
            }
        }
    }
}

fun Message.toEntity(): MessageEntity {
    return MessageEntity(
        id = id,
        roomId = roomId,
        message = message,
        timestamp = timestamp,
        senderId = sender?.id,
        updatedAt = updatedAt,
        editedAt = editedAt,
        editedBy = editedBy?.id,
        senderAlias = senderAlias,
        avatar = avatar,
        type = type.asString(),
        groupable = groupable,
        parseUrls = parseUrls,
        pinned = pinned,
        role = role,
        synced = synced
    )
}