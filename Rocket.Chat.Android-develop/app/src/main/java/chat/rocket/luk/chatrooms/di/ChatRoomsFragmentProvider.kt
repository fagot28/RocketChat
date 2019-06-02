package chat.rocket.luk.chatrooms.di

import chat.rocket.luk.chatrooms.ui.ChatRoomsFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChatRoomsFragmentProvider {

    @ContributesAndroidInjector(modules = [ChatRoomsFragmentModule::class])
    @PerFragment
    abstract fun provideChatRoomsFragment(): ChatRoomsFragment
}