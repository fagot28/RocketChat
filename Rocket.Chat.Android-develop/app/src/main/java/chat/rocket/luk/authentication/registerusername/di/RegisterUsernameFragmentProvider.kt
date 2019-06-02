package chat.rocket.luk.authentication.registerusername.di

import chat.rocket.luk.authentication.registerusername.ui.RegisterUsernameFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RegisterUsernameFragmentProvider {

    @ContributesAndroidInjector(modules = [RegisterUsernameFragmentModule::class])
    @PerFragment
    abstract fun provideRegisterUsernameFragment(): RegisterUsernameFragment
}