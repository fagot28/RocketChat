package chat.rocket.luk.authentication.signup.di

import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.authentication.signup.presentation.SignupView
import chat.rocket.luk.authentication.signup.ui.SignupFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class SignupFragmentModule {

    @Provides
    @PerFragment
    fun signupView(frag: SignupFragment): SignupView = frag

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: SignupFragment): LifecycleOwner = frag
}