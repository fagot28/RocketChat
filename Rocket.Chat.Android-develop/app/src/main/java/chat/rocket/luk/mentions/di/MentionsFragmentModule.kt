package chat.rocket.luk.mentions.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.mentions.presentention.MentionsView
import chat.rocket.luk.mentions.ui.MentionsFragment
import dagger.Module
import dagger.Provides

@Module
class MentionsFragmentModule {

    @Provides
    @PerFragment
    fun provideMentionsView(frag: MentionsFragment): MentionsView {
        return frag
    }
}