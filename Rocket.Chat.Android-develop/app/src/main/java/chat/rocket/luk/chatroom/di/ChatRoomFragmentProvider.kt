package chat.rocket.luk.chatroom.di

import chat.rocket.luk.chatroom.ui.ChatRoomFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChatRoomFragmentProvider {

    @ContributesAndroidInjector(modules = [ChatRoomFragmentModule::class])
    @PerFragment
    abstract fun provideChatRoomFragment(): ChatRoomFragment
}