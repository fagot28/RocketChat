package chat.rocket.luk.settings.password.di

import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.core.lifecycle.CancelStrategy
import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.settings.password.presentation.PasswordView
import chat.rocket.luk.settings.password.ui.PasswordFragment
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Job

@Module
class PasswordFragmentModule {

    @Provides
    @PerFragment
    fun provideJob() = Job()

    @Provides
    @PerFragment
    fun passwordView(frag: PasswordFragment): PasswordView {
        return frag
    }

    @Provides
    @PerFragment
    fun settingsLifecycleOwner(frag: PasswordFragment): LifecycleOwner {
        return frag
    }

    @Provides
    @PerFragment
    fun provideCancelStrategy(owner: LifecycleOwner, jobs: Job): CancelStrategy {
        return CancelStrategy(owner, jobs)
    }
}
