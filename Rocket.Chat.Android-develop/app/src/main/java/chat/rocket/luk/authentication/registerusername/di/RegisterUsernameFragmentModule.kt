package chat.rocket.luk.authentication.registerusername.di

import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.authentication.registerusername.presentation.RegisterUsernameView
import chat.rocket.luk.authentication.registerusername.ui.RegisterUsernameFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class RegisterUsernameFragmentModule {

    @Provides
    @PerFragment
    fun registerUsernameView(frag: RegisterUsernameFragment): RegisterUsernameView = frag

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: RegisterUsernameFragment): LifecycleOwner = frag
}