package chat.rocket.luk.profile.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.profile.ui.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ProfileFragmentProvider {

    @ContributesAndroidInjector(modules = [ProfileFragmentModule::class])
    @PerFragment
    abstract fun provideProfileFragment(): ProfileFragment
}