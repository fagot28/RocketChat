package chat.rocket.luk.authentication.login.di

import chat.rocket.luk.authentication.login.ui.LoginFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class LoginFragmentProvider {

    @ContributesAndroidInjector(modules = [LoginFragmentModule::class])
    @PerFragment
    abstract fun provideLoginFragment(): LoginFragment
}