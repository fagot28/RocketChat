package chat.rocket.luk.favoritemessages.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.favoritemessages.ui.FavoriteMessagesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteMessagesFragmentProvider {

    @ContributesAndroidInjector(modules = [FavoriteMessagesFragmentModule::class])
    @PerFragment
    abstract fun provideFavoriteMessageFragment(): FavoriteMessagesFragment
}