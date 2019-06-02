package chat.rocket.luk.authentication.server.di

import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.authentication.server.presentation.ServerView
import chat.rocket.luk.authentication.server.ui.ServerFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class ServerFragmentModule {

    @Provides
    @PerFragment
    fun serverView(frag: ServerFragment): ServerView = frag

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: ServerFragment): LifecycleOwner = frag
}