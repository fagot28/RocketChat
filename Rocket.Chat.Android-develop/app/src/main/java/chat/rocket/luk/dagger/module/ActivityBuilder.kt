package chat.rocket.luk.dagger.module

import chat.rocket.luk.authentication.di.AuthenticationModule
import chat.rocket.luk.authentication.login.di.LoginFragmentProvider
import chat.rocket.luk.authentication.loginoptions.di.LoginOptionsFragmentProvider
import chat.rocket.luk.authentication.onboarding.di.OnBoardingFragmentProvider
import chat.rocket.luk.authentication.registerusername.di.RegisterUsernameFragmentProvider
import chat.rocket.luk.authentication.resetpassword.di.ResetPasswordFragmentProvider
import chat.rocket.luk.authentication.server.di.ServerFragmentProvider
import chat.rocket.luk.authentication.signup.di.SignupFragmentProvider
import chat.rocket.luk.authentication.twofactor.di.TwoFAFragmentProvider
import chat.rocket.luk.authentication.ui.AuthenticationActivity
import chat.rocket.luk.chatdetails.di.ChatDetailsFragmentProvider
import chat.rocket.luk.chatinformation.di.MessageInfoFragmentProvider
import chat.rocket.luk.chatinformation.ui.MessageInfoActivity
import chat.rocket.luk.chatroom.di.ChatRoomFragmentProvider
import chat.rocket.luk.chatroom.di.ChatRoomModule
import chat.rocket.luk.chatroom.ui.ChatRoomActivity
import chat.rocket.luk.chatrooms.di.ChatRoomsFragmentProvider
import chat.rocket.luk.createchannel.di.CreateChannelProvider
import chat.rocket.luk.dagger.scope.PerActivity
import chat.rocket.luk.directory.di.DirectoryFragmentProvider
import chat.rocket.luk.draw.main.di.DrawModule
import chat.rocket.luk.draw.main.ui.DrawingActivity
import chat.rocket.luk.favoritemessages.di.FavoriteMessagesFragmentProvider
import chat.rocket.luk.files.di.FilesFragmentProvider
import chat.rocket.luk.main.di.MainModule
import chat.rocket.luk.main.ui.MainActivity
import chat.rocket.luk.members.di.MembersFragmentProvider
import chat.rocket.luk.mentions.di.MentionsFragmentProvider
import chat.rocket.luk.pinnedmessages.di.PinnedMessagesFragmentProvider
import chat.rocket.luk.profile.di.ProfileFragmentProvider
import chat.rocket.luk.server.di.ChangeServerModule
import chat.rocket.luk.server.ui.ChangeServerActivity
import chat.rocket.luk.servers.di.ServersBottomSheetFragmentProvider
import chat.rocket.luk.settings.di.SettingsFragmentProvider
import chat.rocket.luk.settings.password.di.PasswordFragmentProvider
import chat.rocket.luk.settings.password.ui.PasswordActivity
import chat.rocket.luk.sortingandgrouping.di.SortingAndGroupingBottomSheetFragmentProvider
import chat.rocket.luk.userdetails.di.UserDetailsFragmentProvider
import chat.rocket.luk.videoconference.di.VideoConferenceModule
import chat.rocket.luk.videoconference.ui.VideoConferenceActivity
import chat.rocket.luk.webview.adminpanel.di.AdminPanelWebViewFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector(
        modules = [AuthenticationModule::class,
            OnBoardingFragmentProvider::class,
            ServerFragmentProvider::class,
            LoginOptionsFragmentProvider::class,
            LoginFragmentProvider::class,
            RegisterUsernameFragmentProvider::class,
            ResetPasswordFragmentProvider::class,
            SignupFragmentProvider::class,
            TwoFAFragmentProvider::class
        ]
    )
    abstract fun bindAuthenticationActivity(): AuthenticationActivity

    @PerActivity
    @ContributesAndroidInjector(
        modules = [MainModule::class,
            ChatRoomsFragmentProvider::class,
            ServersBottomSheetFragmentProvider::class,
            SortingAndGroupingBottomSheetFragmentProvider::class,
            CreateChannelProvider::class,
            ProfileFragmentProvider::class,
            SettingsFragmentProvider::class,
            AdminPanelWebViewFragmentProvider::class,
            DirectoryFragmentProvider::class
        ]
    )
    abstract fun bindMainActivity(): MainActivity

    @PerActivity
    @ContributesAndroidInjector(
        modules = [ChatRoomModule::class,
            ChatRoomFragmentProvider::class,
            UserDetailsFragmentProvider::class,
            ChatDetailsFragmentProvider::class,
            MembersFragmentProvider::class,
            MentionsFragmentProvider::class,
            PinnedMessagesFragmentProvider::class,
            FavoriteMessagesFragmentProvider::class,
            FilesFragmentProvider::class
        ]
    )
    abstract fun bindChatRoomActivity(): ChatRoomActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [PasswordFragmentProvider::class])
    abstract fun bindPasswordActivity(): PasswordActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [ChangeServerModule::class])
    abstract fun bindChangeServerActivity(): ChangeServerActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [MessageInfoFragmentProvider::class])
    abstract fun bindMessageInfoActiviy(): MessageInfoActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [DrawModule::class])
    abstract fun bindDrawingActivity(): DrawingActivity

    @PerActivity
    @ContributesAndroidInjector(modules = [VideoConferenceModule::class])
    abstract fun bindVideoConferenceActivity(): VideoConferenceActivity
}
