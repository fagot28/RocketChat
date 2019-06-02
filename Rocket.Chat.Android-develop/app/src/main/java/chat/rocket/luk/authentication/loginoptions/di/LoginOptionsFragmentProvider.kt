package chat.rocket.luk.authentication.loginoptions.di

import chat.rocket.luk.authentication.loginoptions.ui.LoginOptionsFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class LoginOptionsFragmentProvider {

    @ContributesAndroidInjector(modules = [LoginOptionsFragmentModule::class])
    @PerFragment
    abstract fun providesLoginOptionFragment(): LoginOptionsFragment
}