package chat.rocket.luk.chatdetails.di

import chat.rocket.luk.chatdetails.ui.ChatDetailsFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ChatDetailsFragmentProvider {

    @ContributesAndroidInjector(modules = [ChatDetailsFragmentModule::class])
    @PerFragment
    abstract fun providesChatDetailsFragment(): ChatDetailsFragment
}