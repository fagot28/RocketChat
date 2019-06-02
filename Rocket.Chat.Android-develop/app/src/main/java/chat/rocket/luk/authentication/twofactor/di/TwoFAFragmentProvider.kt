package chat.rocket.luk.authentication.twofactor.di

import chat.rocket.luk.authentication.twofactor.ui.TwoFAFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class TwoFAFragmentProvider {

    @ContributesAndroidInjector(modules = [TwoFAFragmentModule::class])
    @PerFragment
    abstract fun provideTwoFAFragment(): TwoFAFragment
}
