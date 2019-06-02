package chat.rocket.luk.createchannel.di

import chat.rocket.luk.createchannel.ui.CreateChannelFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class CreateChannelProvider {

    @ContributesAndroidInjector(modules = [CreateChannelModule::class])
    @PerFragment
    abstract fun provideCreateChannelFragment(): CreateChannelFragment
}