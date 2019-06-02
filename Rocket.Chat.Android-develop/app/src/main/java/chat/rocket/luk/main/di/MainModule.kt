package chat.rocket.luk.main.di

import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.core.behaviours.AppLanguageView
import chat.rocket.luk.core.lifecycle.CancelStrategy
import chat.rocket.luk.dagger.scope.PerActivity
import chat.rocket.luk.main.presentation.MainNavigator
import chat.rocket.luk.main.ui.MainActivity
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Job

@Module
class MainModule {

    @Provides
    @PerActivity
    fun provideMainNavigator(activity: MainActivity) = MainNavigator(activity)

    @Provides
    @PerActivity
    fun appLanguageView(activity: MainActivity): AppLanguageView {
        return activity
    }

    @Provides
    @PerActivity
    fun provideJob() = Job()

    @Provides
    fun provideLifecycleOwner(activity: MainActivity): LifecycleOwner = activity

    @Provides
    fun provideCancelStrategy(owner: LifecycleOwner, jobs: Job): CancelStrategy =
        CancelStrategy(owner, jobs)
}