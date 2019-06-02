package chat.rocket.luk.push.di

import chat.rocket.luk.dagger.module.AppModule
import chat.rocket.luk.push.FirebaseMessagingService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class FirebaseMessagingServiceProvider {
    @ContributesAndroidInjector(modules = [AppModule::class])
    abstract fun provideFirebaseMessagingService(): FirebaseMessagingService
}