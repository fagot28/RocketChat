package chat.rocket.luk.authentication.onboarding.di

import chat.rocket.luk.authentication.onboarding.ui.OnBoardingFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class OnBoardingFragmentProvider {

    @ContributesAndroidInjector(modules = [OnBoardingFragmentModule::class])
    @PerFragment
    abstract fun provideOnBoardingFragment(): OnBoardingFragment
}