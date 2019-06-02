package chat.rocket.luk.chatrooms.domain

import chat.rocket.luk.db.DatabaseManager
import chat.rocket.luk.util.retryIO
import chat.rocket.core.RocketChatClient
import chat.rocket.core.internal.rest.chatRooms
import timber.log.Timber

class FetchChatRoomsInteractor(
    private val client: RocketChatClient,
    private val dbManager: DatabaseManager
) {

    suspend fun refreshChatRooms() {
        val rooms = retryIO("fetch chatRooms", times = 10,
            initialDelay = 200, maxDelay = 2000) {
            client.chatRooms().update
        }

        Timber.d("Refreshing rooms: $rooms")
        dbManager.processRooms(rooms)
    }
}