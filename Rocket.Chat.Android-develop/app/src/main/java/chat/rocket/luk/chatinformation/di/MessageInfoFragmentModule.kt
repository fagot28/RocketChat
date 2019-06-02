package chat.rocket.luk.chatinformation.di

import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.chatinformation.presentation.MessageInfoView
import chat.rocket.luk.chatinformation.ui.MessageInfoFragment
import chat.rocket.luk.core.lifecycle.CancelStrategy
import chat.rocket.luk.dagger.scope.PerFragment
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Job

@Module
class MessageInfoFragmentModule {

    @Provides
    @PerFragment
    fun provideJob() = Job()

    @Provides
    @PerFragment
    fun messageInfoView(frag: MessageInfoFragment): MessageInfoView {
        return frag
    }

    @Provides
    @PerFragment
    fun provideLifecycleOwner(frag: MessageInfoFragment): LifecycleOwner {
        return frag
    }

    @Provides
    @PerFragment
    fun provideCancelStrategy(owner: LifecycleOwner, jobs: Job): CancelStrategy {
        return CancelStrategy(owner, jobs)
    }
}
