package chat.rocket.luk.pinnedmessages.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.pinnedmessages.presentation.PinnedMessagesView
import chat.rocket.luk.pinnedmessages.ui.PinnedMessagesFragment
import dagger.Module
import dagger.Provides

@Module
class PinnedMessagesFragmentModule {

    @Provides
    @PerFragment
    fun providePinnedMessagesView(frag: PinnedMessagesFragment): PinnedMessagesView {
        return frag
    }
}