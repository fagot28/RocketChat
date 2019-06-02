package chat.rocket.luk.webview.adminpanel.di

import chat.rocket.luk.webview.adminpanel.ui.AdminPanelWebViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AdminPanelWebViewFragmentProvider {

    @ContributesAndroidInjector
    abstract fun provideAdminPanelWebViewFragment(): AdminPanelWebViewFragment
}