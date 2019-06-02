package chat.rocket.luk.directory.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.directory.presentation.DirectoryView
import chat.rocket.luk.directory.ui.DirectoryFragment
import dagger.Module
import dagger.Provides

@Module
class DirectoryFragmentModule {

    @Provides
    @PerFragment
    fun directoryView(frag: DirectoryFragment): DirectoryView = frag
}