package chat.rocket.luk.dagger.module

import chat.rocket.luk.push.DeleteReceiver
import chat.rocket.luk.push.DirectReplyReceiver
import chat.rocket.luk.push.DirectReplyReceiverProvider
import chat.rocket.luk.push.di.DeleteReceiverProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ReceiverBuilder {

    @ContributesAndroidInjector(modules = [DeleteReceiverProvider::class])
    abstract fun bindDeleteReceiver(): DeleteReceiver

    @ContributesAndroidInjector(modules = [DirectReplyReceiverProvider::class])
    abstract fun bindDirectReplyReceiver(): DirectReplyReceiver
}