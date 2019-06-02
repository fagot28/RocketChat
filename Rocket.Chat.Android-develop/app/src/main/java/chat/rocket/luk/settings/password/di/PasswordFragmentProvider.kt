package chat.rocket.luk.settings.password.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.settings.password.ui.PasswordFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PasswordFragmentProvider {
    @ContributesAndroidInjector(modules = [PasswordFragmentModule::class])
    @PerFragment
    abstract fun providePasswordFragment(): PasswordFragment
}