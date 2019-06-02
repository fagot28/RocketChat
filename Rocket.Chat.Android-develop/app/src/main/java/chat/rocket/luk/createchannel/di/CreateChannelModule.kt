package chat.rocket.luk.createchannel.di

import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.createchannel.presentation.CreateChannelView
import chat.rocket.luk.createchannel.ui.CreateChannelFragment
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides

@Module
class CreateChannelModule {

    @Provides
    @PerFragment
    fun createChannelView(fragment: CreateChannelFragment): CreateChannelView {
        return fragment
    }

    @Provides
    @PerFragment
    fun provideLifecycleOwner(fragment: CreateChannelFragment): LifecycleOwner {
        return fragment
    }
}