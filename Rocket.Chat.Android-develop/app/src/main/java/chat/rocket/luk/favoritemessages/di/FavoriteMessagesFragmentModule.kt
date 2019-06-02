package chat.rocket.luk.favoritemessages.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.favoritemessages.presentation.FavoriteMessagesView
import chat.rocket.luk.favoritemessages.ui.FavoriteMessagesFragment
import dagger.Module
import dagger.Provides

@Module
class FavoriteMessagesFragmentModule {

    @Provides
    @PerFragment
    fun provideFavoriteMessagesView(frag: FavoriteMessagesFragment): FavoriteMessagesView {
        return frag
    }
}