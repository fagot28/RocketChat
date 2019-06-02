package chat.rocket.luk.directory.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.directory.ui.DirectoryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DirectoryFragmentProvider {

    @ContributesAndroidInjector(modules = [DirectoryFragmentModule::class])
    @PerFragment
    abstract fun provideDirectoryFragment(): DirectoryFragment

}