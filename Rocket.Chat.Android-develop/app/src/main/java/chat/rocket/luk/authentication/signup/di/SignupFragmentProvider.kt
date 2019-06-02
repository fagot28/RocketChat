package chat.rocket.luk.authentication.signup.di

import chat.rocket.luk.authentication.signup.ui.SignupFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SignupFragmentProvider {

    @ContributesAndroidInjector(modules = [SignupFragmentModule::class])
    @PerFragment
    abstract fun provideSignupFragment(): SignupFragment
}