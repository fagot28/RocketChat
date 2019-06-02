package chat.rocket.luk.pinnedmessages.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.pinnedmessages.ui.PinnedMessagesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PinnedMessagesFragmentProvider {

    @ContributesAndroidInjector(modules = [PinnedMessagesFragmentModule::class])
    @PerFragment
    abstract fun providePinnedMessageFragment(): PinnedMessagesFragment
}