package chat.rocket.luk.draw.dagger.module

import chat.rocket.luk.draw.main.di.DrawModule
import chat.rocket.luk.draw.main.ui.DrawingActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [DrawModule::class])
    abstract fun contributeDrawingActivityInjector(): DrawingActivity
}