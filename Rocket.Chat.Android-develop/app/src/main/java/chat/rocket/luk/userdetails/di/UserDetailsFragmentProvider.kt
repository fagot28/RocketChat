package chat.rocket.luk.userdetails.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.userdetails.ui.UserDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UserDetailsFragmentProvider {

    @ContributesAndroidInjector(modules = [UserDetailsFragmentModule::class])
    @PerFragment
    abstract fun provideUserDetailsFragment(): UserDetailsFragment
}
