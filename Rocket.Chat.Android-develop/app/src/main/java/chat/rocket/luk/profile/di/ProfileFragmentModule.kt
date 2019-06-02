package chat.rocket.luk.profile.di

import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.profile.presentation.ProfileView
import chat.rocket.luk.profile.ui.ProfileFragment
import dagger.Module
import dagger.Provides

@Module
class ProfileFragmentModule {

    @Provides
    @PerFragment
    fun profileView(frag: ProfileFragment): ProfileView {
        return frag
    }

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: ProfileFragment): LifecycleOwner {
        return frag
    }
}