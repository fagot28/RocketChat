package chat.rocket.luk.settings.di

import androidx.lifecycle.LifecycleOwner
import chat.rocket.luk.dagger.scope.PerFragment
import chat.rocket.luk.settings.presentation.SettingsView
import chat.rocket.luk.settings.ui.SettingsFragment
import dagger.Module
import dagger.Provides

@Module
class SettingsFragmentModule {

    @Provides
    @PerFragment
    fun settingsView(frag: SettingsFragment): SettingsView {
        return frag
    }

    @Provides
    @PerFragment
    fun settingsLifecycleOwner(fragment: SettingsFragment): LifecycleOwner {
        return fragment
    }
}