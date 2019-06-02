package chat.rocket.luk.server.di

import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.core.lifecycle.CancelStrategy
import chat.rocket.luk.dagger.scope.PerActivity
import chat.rocket.luk.server.presentation.ChangeServerNavigator
import chat.rocket.luk.server.presentation.ChangeServerView
import chat.rocket.luk.server.ui.ChangeServerActivity
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Job

@Module
class ChangeServerModule {

    @Provides
    @PerActivity
    fun provideJob() = Job()

    @Provides
    @PerActivity
    fun provideChangeServerNavigator(activity: ChangeServerActivity) = ChangeServerNavigator(activity)

    @Provides
    @PerActivity
    fun ChangeServerView(activity: ChangeServerActivity): ChangeServerView {
        return activity
    }

    @Provides
    fun provideLifecycleOwner(activity: ChangeServerActivity): LifecycleOwner = activity

    @Provides
    fun provideCancelStrategy(owner: LifecycleOwner, jobs: Job): CancelStrategy = CancelStrategy(owner, jobs)
}