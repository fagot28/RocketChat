package chat.rocket.luk.files.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.files.presentation.FilesView
import chat.rocket.luk.files.ui.FilesFragment
import dagger.Module
import dagger.Provides

@Module
class FilesFragmentModule {

    @Provides
    @PerFragment
    fun provideFilesView(frag: FilesFragment): FilesView {
        return frag
    }
}