package chat.rocket.luk.chatroom.di

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.chatroom.presentation.ChatRoomView
import chat.rocket.luk.chatroom.ui.ChatRoomFragment
import chat.rocket.luk.chatrooms.adapter.RoomUiModelMapper
import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.db.ChatRoomDao
import chat.rocket.luk.db.DatabaseManager
import chat.rocket.luk.db.UserDao
import chat.rocket.luk.server.domain.GetCurrentUserInteractor
import chat.rocket.luk.server.domain.PermissionsInteractor
import chat.rocket.luk.server.domain.SettingsRepository
import chat.rocket.luk.server.domain.TokenRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ChatRoomFragmentModule {

    @Provides
    @PerFragment
    fun chatRoomView(frag: ChatRoomFragment): ChatRoomView {
        return frag
    }

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: ChatRoomFragment): LifecycleOwner {
        return frag
    }

    @Provides
    @PerFragment
    fun provideChatRoomDao(manager: DatabaseManager): ChatRoomDao = manager.chatRoomDao()

    @Provides
    @PerFragment
    fun provideUserDao(manager: DatabaseManager): UserDao = manager.userDao()

    @Provides
    @PerFragment
    fun provideGetCurrentUserInteractor(
        tokenRepository: TokenRepository,
        @Named("currentServer") serverUrl: String,
        userDao: UserDao
    ): GetCurrentUserInteractor {
        return GetCurrentUserInteractor(tokenRepository, serverUrl, userDao)
    }

    @Provides
    @PerFragment
    fun provideRoomMapper(
        context: Application,
        repository: SettingsRepository,
        userInteractor: GetCurrentUserInteractor,
        tokenRepository: TokenRepository,
        @Named("currentServer") serverUrl: String,
        permissionsInteractor: PermissionsInteractor
    ): RoomUiModelMapper {
        return RoomUiModelMapper(
            context,
            repository.get(serverUrl),
            userInteractor,
            tokenRepository,
            serverUrl,
            permissionsInteractor
        )
    }
}
