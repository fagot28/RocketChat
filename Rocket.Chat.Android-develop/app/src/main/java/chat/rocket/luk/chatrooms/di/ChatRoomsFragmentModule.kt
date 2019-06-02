package chat.rocket.luk.chatrooms.di

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.chatrooms.adapter.RoomUiModelMapper
import chat.rocket.luk.chatrooms.domain.FetchChatRoomsInteractor
import chat.rocket.luk.chatrooms.presentation.ChatRoomsView
import chat.rocket.luk.chatrooms.ui.ChatRoomsFragment
import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.db.ChatRoomDao
import chat.rocket.luk.db.DatabaseManager
import chat.rocket.luk.db.UserDao
import chat.rocket.luk.server.domain.GetCurrentUserInteractor
import chat.rocket.luk.server.domain.PermissionsInteractor
import chat.rocket.luk.server.domain.PublicSettings
import chat.rocket.luk.server.domain.SettingsRepository
import chat.rocket.luk.server.domain.TokenRepository
import chat.rocket.luk.server.infrastructure.ConnectionManager
import chat.rocket.luk.server.infrastructure.ConnectionManagerFactory
import chat.rocket.luk.server.infrastructure.RocketChatClientFactory
import chat.rocket.core.RocketChatClient
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ChatRoomsFragmentModule {

    @Provides
    @PerFragment
    fun chatRoomsView(frag: ChatRoomsFragment): ChatRoomsView {
        return frag
    }

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: ChatRoomsFragment): LifecycleOwner {
        return frag
    }

    @Provides
    @PerFragment
    fun provideRocketChatClient(
        factory: RocketChatClientFactory,
        @Named("currentServer") currentServer: String
    ): RocketChatClient {
        return factory.get(currentServer)
    }

    @Provides
    @PerFragment
    fun provideChatRoomDao(manager: DatabaseManager): ChatRoomDao = manager.chatRoomDao()

    @Provides
    @PerFragment
    fun provideUserDao(manager: DatabaseManager): UserDao = manager.userDao()

    @Provides
    @PerFragment
    fun provideConnectionManager(
        factory: ConnectionManagerFactory,
        @Named("currentServer") currentServer: String
    ): ConnectionManager {
        return factory.create(currentServer)
    }

    @Provides
    @PerFragment
    fun provideFetchChatRoomsInteractor(
        client: RocketChatClient,
        dbManager: DatabaseManager
    ): FetchChatRoomsInteractor {
        return FetchChatRoomsInteractor(client, dbManager)
    }

    @Provides
    @PerFragment
    fun providePublicSettings(
        repository: SettingsRepository,
        @Named("currentServer") currentServer: String
    ): PublicSettings {
        return repository.get(currentServer)
    }

    @Provides
    @PerFragment
    fun provideRoomMapper(
        context: Application,
        settingsRepository: SettingsRepository,
        userInteractor: GetCurrentUserInteractor,
        tokenRepository: TokenRepository,
        @Named("currentServer") serverUrl: String,
        permissionsInteractor: PermissionsInteractor
    ): RoomUiModelMapper {
        return RoomUiModelMapper(
            context,
            settingsRepository.get(serverUrl),
            userInteractor,
            tokenRepository,
            serverUrl,
            permissionsInteractor
        )
    }

    @Provides
    @PerFragment
    fun provideGetCurrentUserInteractor(
        tokenRepository: TokenRepository,
        @Named("currentServer") serverUrl: String,
        userDao: UserDao
    ): GetCurrentUserInteractor {
        return GetCurrentUserInteractor(tokenRepository, serverUrl, userDao)
    }
}