package chat.rocket.luk.authentication.server.di

import chat.rocket.luk.authentication.server.ui.ServerFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServerFragmentProvider {

    @ContributesAndroidInjector(modules = [ServerFragmentModule::class])
    @PerFragment
    abstract fun provideServerFragment(): ServerFragment
}