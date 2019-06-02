package chat.rocket.luk.servers.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.servers.presentation.ServersView
import chat.rocket.luk.servers.ui.ServersBottomSheetFragment
import dagger.Module
import dagger.Provides

@Module
class ServersBottomSheetFragmentModule {

    @Provides
    @PerFragment
    fun serversView(frag: ServersBottomSheetFragment): ServersView = frag
}