package chat.rocket.luk.chatinformation.di

import chat.rocket.luk.chatinformation.ui.MessageInfoFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MessageInfoFragmentProvider {

    @ContributesAndroidInjector(modules = [MessageInfoFragmentModule::class])
    @PerFragment
    abstract fun provideMessageInfoFragment(): MessageInfoFragment
}
