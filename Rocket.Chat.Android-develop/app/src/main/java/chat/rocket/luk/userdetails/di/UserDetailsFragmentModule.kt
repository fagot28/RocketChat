package chat.rocket.luk.userdetails.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.userdetails.presentation.UserDetailsView
import chat.rocket.luk.userdetails.ui.UserDetailsFragment
import dagger.Module
import dagger.Provides

@Module
class UserDetailsFragmentModule {

    @Provides
    @PerFragment
    fun provideUserDetailsView(frag: UserDetailsFragment): UserDetailsView = frag
}