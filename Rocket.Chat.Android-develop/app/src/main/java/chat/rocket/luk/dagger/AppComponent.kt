package chat.rocket.luk.dagger

import android.app.Application
import chat.rocket.luk.app.RocketChatApplication
import chat.rocket.luk.chatroom.service.MessageService
import chat.rocket.luk.dagger.module.ActivityBuilder
import chat.rocket.luk.dagger.module.AndroidWorkerInjectionModule
import chat.rocket.luk.dagger.module.AppModule
import chat.rocket.luk.dagger.module.ReceiverBuilder
import chat.rocket.luk.dagger.module.ServiceBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityBuilder::class,
        ServiceBuilder::class,
        ReceiverBuilder::class,
        AndroidWorkerInjectionModule::class]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: RocketChatApplication)

    fun inject(service: MessageService)
}
