package chat.rocket.luk.chatdetails.di

import chat.rocket.luk.chatdetails.presentation.ChatDetailsView
import chat.rocket.luk.chatdetails.ui.ChatDetailsFragment
import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.db.ChatRoomDao
import chat.rocket.luk.db.DatabaseManager
import dagger.Module
import dagger.Provides

@Module
class ChatDetailsFragmentModule {

    @Provides
    @PerFragment
    fun chatDetailsView(frag: ChatDetailsFragment): ChatDetailsView {
        return frag
    }

    @Provides
    @PerFragment
    fun provideChatRoomDao(manager: DatabaseManager): ChatRoomDao = manager.chatRoomDao()
}