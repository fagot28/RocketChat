package chat.rocket.luk.chatdetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import chat.rocket.luk.db.ChatRoomDao
import javax.inject.Inject

class ChatDetailsViewModelFactory @Inject constructor(
    private val chatRoomDao: ChatRoomDao
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) = ChatDetailsViewModel(chatRoomDao) as T
}