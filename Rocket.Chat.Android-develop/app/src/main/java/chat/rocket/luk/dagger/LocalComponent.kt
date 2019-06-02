package chat.rocket.luk.dagger

import android.content.Context
import chat.rocket.luk.chatroom.adapter.MessageReactionsAdapter
import chat.rocket.luk.dagger.module.LocalModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [LocalModule::class])
interface LocalComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(applicationContext: Context): Builder

        fun build(): LocalComponent
    }

    fun inject(adapter: MessageReactionsAdapter.ReactionViewHolder)
    fun inject(adapter: MessageReactionsAdapter.AddReactionViewHolder)

    /*@Component.Builder
    abstract class Builder : AndroidInjector.Builder<RocketChatApplication>()*/
}
