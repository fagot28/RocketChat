package chat.rocket.luk.authentication.login.di

import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.authentication.login.presentation.LoginView
import chat.rocket.luk.authentication.login.ui.LoginFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class LoginFragmentModule {

    @Provides
    @PerFragment
    fun loginView(frag: LoginFragment): LoginView = frag

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: LoginFragment): LifecycleOwner = frag
}