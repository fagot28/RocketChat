package chat.rocket.luk.files.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.files.ui.FilesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FilesFragmentProvider {

    @ContributesAndroidInjector(modules = [FilesFragmentModule::class])
    @PerFragment
    abstract fun provideFilesFragment(): FilesFragment
}