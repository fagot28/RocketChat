package chat.rocket.luk.push

import chat.rocket.luk.dagger.module.AppModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DirectReplyReceiverProvider {
    @ContributesAndroidInjector(modules = [AppModule::class])
    abstract fun provideDirectReplyReceiver(): DirectReplyReceiver
}