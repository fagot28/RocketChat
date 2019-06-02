package chat.rocket.luk.settings.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.settings.ui.SettingsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SettingsFragmentProvider {

    @ContributesAndroidInjector(modules = [SettingsFragmentModule::class])
    @PerFragment
    abstract fun provideSettingsFragment(): SettingsFragment
}