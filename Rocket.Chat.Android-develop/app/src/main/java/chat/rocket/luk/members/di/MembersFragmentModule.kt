package chat.rocket.luk.members.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.members.presentation.MembersView
import chat.rocket.luk.members.ui.MembersFragment
import dagger.Module
import dagger.Provides

@Module
class MembersFragmentModule {

    @Provides
    @PerFragment
    fun membersView(frag: MembersFragment): MembersView {
        return frag
    }
}