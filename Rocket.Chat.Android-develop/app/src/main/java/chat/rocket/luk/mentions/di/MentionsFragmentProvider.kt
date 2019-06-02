package chat.rocket.luk.mentions.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.mentions.ui.MentionsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MentionsFragmentProvider {

    @ContributesAndroidInjector(modules = [MentionsFragmentModule::class])
    @PerFragment
    abstract fun provideMentionsFragment(): MentionsFragment
}