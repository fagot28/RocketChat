package chat.rocket.luk.servers.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.servers.ui.ServersBottomSheetFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServersBottomSheetFragmentProvider {

    @ContributesAndroidInjector(modules = [ServersBottomSheetFragmentModule::class])
    @PerFragment
    abstract fun provideServersBottomSheetFragment(): ServersBottomSheetFragment
}