package chat.rocket.luk.push.di

import chat.rocket.luk.push.worker.TokenRegistrationWorker
import dagger.Subcomponent
import dagger.android.AndroidInjector

@Subcomponent
interface TokenRegistrationSubComponent : AndroidInjector<TokenRegistrationWorker> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TokenRegistrationWorker>()
}