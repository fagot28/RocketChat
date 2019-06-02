package chat.rocket.luk.chatroom.di

import chat.rocket.luk.chatroom.service.MessageService
import chat.rocket.luk.dagger.module.AppModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module abstract class MessageServiceProvider {

    @ContributesAndroidInjector(modules = [AppModule::class])
    abstract fun provideMessageService(): MessageService
}