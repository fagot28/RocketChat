package chat.rocket.luk.authentication.loginoptions.di

import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.authentication.loginoptions.presentation.LoginOptionsView
import chat.rocket.luk.authentication.loginoptions.ui.LoginOptionsFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class LoginOptionsFragmentModule {

    @Provides
    @PerFragment
    fun loginOptionsView(frag: LoginOptionsFragment): LoginOptionsView = frag

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: LoginOptionsFragment): LifecycleOwner = frag
}