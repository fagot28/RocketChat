package chat.rocket.luk.authentication.di

import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.authentication.presentation.AuthenticationNavigator
import chat.rocket.luk.authentication.ui.AuthenticationActivity
import chat.rocket.luk.core.lifecycle.CancelStrategy
import chat.rocket.luk.dagger.scope.PerActivity
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Job

@Module
class AuthenticationModule {

    @Provides
    @PerActivity
    fun provideAuthenticationNavigator(activity: AuthenticationActivity) =
        AuthenticationNavigator(activity)

    @Provides
    @PerActivity
    fun provideJob() = Job()

    @Provides
    @PerActivity
    fun provideLifecycleOwner(activity: AuthenticationActivity): LifecycleOwner = activity

    @Provides
    @PerActivity
    fun provideCancelStrategy(owner: LifecycleOwner, jobs: Job): CancelStrategy =
        CancelStrategy(owner, jobs)
}