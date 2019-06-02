package chat.rocket.luk.chatrooms.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import chat.rocket.luk.chatrooms.adapter.RoomUiModelMapper
import chat.rocket.luk.chatrooms.domain.FetchChatRoomsInteractor
import chat.rocket.luk.chatrooms.infrastructure.ChatRoomsRepository
import chat.rocket.luk.server.infrastructure.ConnectionManager
import javax.inject.Inject

class ChatRoomsViewModelFactory @Inject constructor(
    private val connectionManager: ConnectionManager,
    private val interactor: FetchChatRoomsInteractor,
    private val repository: ChatRoomsRepository,
    private val mapper: RoomUiModelMapper
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
            ChatRoomsViewModel(connectionManager, interactor, repository, mapper) as T
}