package chat.rocket.luk.authentication.resetpassword.di

import chat.rocket.luk.authentication.resetpassword.ui.ResetPasswordFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ResetPasswordFragmentProvider {

    @ContributesAndroidInjector(modules = [ResetPasswordFragmentModule::class])
    @PerFragment
    abstract fun provideResetPasswordFragment(): ResetPasswordFragment
}