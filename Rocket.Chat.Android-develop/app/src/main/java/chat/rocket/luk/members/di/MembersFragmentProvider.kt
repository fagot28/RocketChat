package chat.rocket.luk.members.di

import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.members.ui.MembersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MembersFragmentProvider {

    @ContributesAndroidInjector(modules = [MembersFragmentModule::class])
    @PerFragment
    abstract fun provideMembersFragment(): MembersFragment
}