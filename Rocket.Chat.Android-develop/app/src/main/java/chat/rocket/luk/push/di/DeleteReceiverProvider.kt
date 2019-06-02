package chat.rocket.luk.push.di

import chat.rocket.luk.dagger.module.AppModule
import chat.rocket.luk.push.DeleteReceiver
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DeleteReceiverProvider {
    @ContributesAndroidInjector(modules = [AppModule::class])
    abstract fun provideDeleteReceiver(): DeleteReceiver
}