package chat.rocket.luk.dagger.module

import android.app.Application
import android.app.NotificationManager
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.content.SharedPreferences
import chat.rocket.luk.BuildConfig
import chat.rocket.luk.R
import chat.rocket.luk.analytics.AnalyticsManager
import chat.rocket.luk.analytics.AnswersAnalytics
import chat.rocket.luk.analytics.GoogleAnalyticsForFirebase
import chat.rocket.luk.authentication.infrastructure.SharedPreferencesMultiServerTokenRepository
import chat.rocket.luk.authentication.infrastructure.SharedPreferencesTokenRepository
import chat.rocket.luk.chatroom.service.MessageService
import chat.rocket.luk.dagger.qualifier.ForAuthentication
import chat.rocket.luk.dagger.qualifier.ForMessages
import chat.rocket.luk.db.DatabaseManager
import chat.rocket.luk.db.DatabaseManagerFactory
import chat.rocket.luk.helper.MessageParser
import chat.rocket.luk.infrastructure.LocalRepository
import chat.rocket.luk.infrastructure.SharedPreferencesLocalRepository
import chat.rocket.luk.push.GroupedPush
import chat.rocket.luk.push.PushManager
import chat.rocket.luk.server.domain.AccountsRepository
import chat.rocket.luk.server.domain.AnalyticsTrackingInteractor
import chat.rocket.luk.server.domain.AnalyticsTrackingRepository
import chat.rocket.luk.server.domain.BasicAuthRepository
import chat.rocket.luk.server.domain.ChatRoomsRepository
import chat.rocket.luk.server.domain.CurrentServerRepository
import chat.rocket.luk.server.domain.GetAccountInteractor
import chat.rocket.luk.server.domain.GetAccountsInteractor
import chat.rocket.luk.server.domain.GetBasicAuthInteractor
import chat.rocket.luk.server.domain.GetCurrentServerInteractor
import chat.rocket.luk.server.domain.GetSettingsInteractor
import chat.rocket.luk.server.domain.JobSchedulerInteractor
import chat.rocket.luk.server.domain.MessagesRepository
import chat.rocket.luk.server.domain.MultiServerTokenRepository
import chat.rocket.luk.server.domain.PermissionsRepository
import chat.rocket.luk.server.domain.SaveBasicAuthInteractor
import chat.rocket.luk.server.domain.SettingsRepository
import chat.rocket.luk.server.domain.SortingAndGroupingRepository
import chat.rocket.luk.server.domain.TokenRepository
import chat.rocket.luk.server.domain.UsersRepository
import chat.rocket.luk.server.infrastructure.CurrentLanguageRepository
import chat.rocket.luk.server.infrastructure.SharedPrefsCurrentLanguageRepository
import chat.rocket.luk.server.infrastructure.DatabaseMessageMapper
import chat.rocket.luk.server.infrastructure.DatabaseMessagesRepository
import chat.rocket.luk.server.infrastructure.JobSchedulerInteractorImpl
import chat.rocket.luk.server.infrastructure.MemoryChatRoomsRepository
import chat.rocket.luk.server.infrastructure.MemoryUsersRepository
import chat.rocket.luk.server.infrastructure.SharedPreferencesAccountsRepository
import chat.rocket.luk.server.infrastructure.SharedPreferencesPermissionsRepository
import chat.rocket.luk.server.infrastructure.SharedPreferencesSettingsRepository
import chat.rocket.luk.server.infrastructure.SharedPrefsAnalyticsTrackingRepository
import chat.rocket.luk.server.infrastructure.SharedPrefsBasicAuthRepository
import chat.rocket.luk.server.infrastructure.SharedPrefsConnectingServerRepository
import chat.rocket.luk.server.infrastructure.SharedPrefsCurrentServerRepository
import chat.rocket.luk.server.infrastructure.SharedPrefsSortingAndGroupingRepository
import chat.rocket.luk.util.AppJsonAdapterFactory
import chat.rocket.luk.util.BasicAuthenticatorInterceptor
import chat.rocket.luk.util.HttpLoggingInterceptor
import chat.rocket.luk.util.TimberLogger
import chat.rocket.common.internal.FallbackSealedClassJsonAdapter
import chat.rocket.common.internal.ISO8601Date
import chat.rocket.common.model.TimestampAdapter
import chat.rocket.common.util.CalendarISO8601Converter
import chat.rocket.common.util.NoOpLogger
import chat.rocket.common.util.PlatformLogger
import chat.rocket.core.internal.AttachmentAdapterFactory
import chat.rocket.core.internal.ReactionsAdapter
import com.facebook.drawee.backends.pipeline.DraweeConfig
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.facebook.imagepipeline.core.ImagePipelineConfig
import com.facebook.imagepipeline.listener.RequestLoggingListener
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import ru.noties.markwon.SpannableConfiguration
import ru.noties.markwon.spans.SpannableTheme
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Timber.d(message)
            }
        })
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            // TODO - change to HEADERS on production...
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        }

        return interceptor
    }

    @Provides
    @Singleton
    fun provideBasicAuthenticatorInterceptor(
        getBasicAuthInteractor: GetBasicAuthInteractor,
        saveBasicAuthInteractor: SaveBasicAuthInteractor
    ): BasicAuthenticatorInterceptor {
        return BasicAuthenticatorInterceptor(
            getBasicAuthInteractor,
            saveBasicAuthInteractor
        )
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        logger: HttpLoggingInterceptor,
        basicAuthenticator: BasicAuthenticatorInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(basicAuthenticator)
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideImagePipelineConfig(
        context: Context,
        okHttpClient: OkHttpClient
    ): ImagePipelineConfig {
        val listeners = setOf(RequestLoggingListener())

        return OkHttpImagePipelineConfigFactory.newBuilder(context, okHttpClient)
            .setRequestListeners(listeners)
            .setDownsampleEnabled(true)
            .experiment().setPartialImageCachingEnabled(true).build()
    }

    @Provides
    @Singleton
    fun provideDraweeConfig(): DraweeConfig {
        return DraweeConfig.newBuilder().build()
    }

    @Provides
    @Singleton
    fun provideTokenRepository(prefs: SharedPreferences, moshi: Moshi): TokenRepository {
        return SharedPreferencesTokenRepository(prefs, moshi)
    }

    @Provides
    @Singleton
    fun providePlatformLogger(): PlatformLogger {
        return TimberLogger
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Application) =
        context.getSharedPreferences("rocket.chat", Context.MODE_PRIVATE)

    @Provides
    @ForMessages
    fun provideMessagesSharedPreferences(context: Application) =
        context.getSharedPreferences("messages", Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideLocalRepository(prefs: SharedPreferences, moshi: Moshi): LocalRepository {
        return SharedPreferencesLocalRepository(prefs, moshi)
    }

    @Provides
    @Singleton
    fun provideCurrentServerRepository(prefs: SharedPreferences): CurrentServerRepository {
        return SharedPrefsCurrentServerRepository(prefs)
    }

    @Provides
    @Singleton
    fun provideAnalyticsTrackingRepository(prefs: SharedPreferences): AnalyticsTrackingRepository {
        return SharedPrefsAnalyticsTrackingRepository(prefs)
    }

    @Provides
    @Singleton
    fun provideSortingAndGroupingRepository(prefs: SharedPreferences): SortingAndGroupingRepository {
        return SharedPrefsSortingAndGroupingRepository(prefs)
    }

    @Provides
    @ForAuthentication
    fun provideConnectingServerRepository(prefs: SharedPreferences): CurrentServerRepository {
        return SharedPrefsConnectingServerRepository(prefs)
    }

    @Provides
    @Singleton
    fun provideCurrentLanguageRepository(prefs: SharedPreferences): CurrentLanguageRepository {
        return SharedPrefsCurrentLanguageRepository(prefs)
    }

    @Provides
    @Singleton
    fun provideSettingsRepository(localRepository: LocalRepository): SettingsRepository {
        return SharedPreferencesSettingsRepository(localRepository)
    }

    @Provides
    @Singleton
    fun providePermissionsRepository(
        localRepository: LocalRepository,
        moshi: Moshi
    ): PermissionsRepository {
        return SharedPreferencesPermissionsRepository(localRepository, moshi)
    }

    @Provides
    @Singleton
    fun provideChatRoomRepository(): ChatRoomsRepository {
        return MemoryChatRoomsRepository()
    }

    @Provides
    @Singleton
    fun provideMoshi(
        logger: PlatformLogger,
        currentServerInteractor: GetCurrentServerInteractor
    ): Moshi {
        val url = currentServerInteractor.get() ?: ""
        return Moshi.Builder()
            .add(FallbackSealedClassJsonAdapter.ADAPTER_FACTORY)
            .add(AppJsonAdapterFactory.INSTANCE)
            .add(AttachmentAdapterFactory(NoOpLogger))
            .add(
                java.lang.Long::class.java,
                ISO8601Date::class.java,
                TimestampAdapter(CalendarISO8601Converter())
            )
            .add(
                Long::class.java,
                ISO8601Date::class.java,
                TimestampAdapter(CalendarISO8601Converter())
            )
            .add(ReactionsAdapter())
            .build()
    }

    @Provides
    @Singleton
    fun provideMultiServerTokenRepository(
        repository: LocalRepository,
        moshi: Moshi
    ): MultiServerTokenRepository {
        return SharedPreferencesMultiServerTokenRepository(repository, moshi)
    }

    @Provides
    fun provideMessageRepository(databaseManager: DatabaseManager): MessagesRepository {
        return DatabaseMessagesRepository(databaseManager, DatabaseMessageMapper(databaseManager))
    }

    @Provides
    @Singleton
    fun provideUserRepository(): UsersRepository {
        return MemoryUsersRepository()
    }

    @Provides
    @Singleton
    fun provideConfiguration(context: Application): SpannableConfiguration {
        val res = context.resources
        return SpannableConfiguration.builder(context)
            .theme(
                SpannableTheme.builder()
                    .blockMargin(0)
                    .linkColor(res.getColor(R.color.colorAccent))
                    .build()
            )
            .build()
    }

    @Provides
    fun provideMessageParser(
        context: Application,
        configuration: SpannableConfiguration,
        serverInteractor: GetCurrentServerInteractor,
        settingsInteractor: GetSettingsInteractor
    ): MessageParser {
        val url = serverInteractor.get()!!
        return MessageParser(context, configuration, settingsInteractor.get(url))
    }

    @Provides
    @Singleton
    fun provideBasicAuthRepository(
        preferences: SharedPreferences,
        moshi: Moshi
    ): BasicAuthRepository =
        SharedPrefsBasicAuthRepository(preferences, moshi)

    @Provides
    @Singleton
    fun provideAccountsRepository(
        preferences: SharedPreferences,
        moshi: Moshi
    ): AccountsRepository =
        SharedPreferencesAccountsRepository(preferences, moshi)

    @Provides
    fun provideNotificationManager(context: Application) =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @Provides
    @Singleton
    fun provideGroupedPush() = GroupedPush()

    @Provides
    @Singleton
    fun providePushManager(
        context: Application,
        groupedPushes: GroupedPush,
        manager: NotificationManager,
        moshi: Moshi,
        getAccountInteractor: GetAccountInteractor,
        getSettingsInteractor: GetSettingsInteractor
    ): PushManager {
        return PushManager(
            groupedPushes,
            manager,
            moshi,
            getAccountInteractor,
            getSettingsInteractor,
            context
        )
    }

    @Provides
    fun provideJobScheduler(context: Application): JobScheduler {
        return context.getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
    }

    @Provides
    fun provideSendMessageJob(context: Application): JobInfo {
        return JobInfo.Builder(
            MessageService.RETRY_SEND_MESSAGE_ID,
            ComponentName(context, MessageService::class.java)
        )
            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
            .build()
    }

    @Provides
    fun provideJobSchedulerInteractor(
        jobScheduler: JobScheduler,
        jobInfo: JobInfo
    ): JobSchedulerInteractor {
        return JobSchedulerInteractorImpl(jobScheduler, jobInfo)
    }

    @Provides
    @Named("currentServer")
    fun provideCurrentServer(currentServerInteractor: GetCurrentServerInteractor): String {
        return currentServerInteractor.get()!!
    }

    @Provides
    fun provideDatabaseManager(
        factory: DatabaseManagerFactory,
        @Named("currentServer") currentServer: String
    ): DatabaseManager {
        return factory.create(currentServer)
    }

    @Provides
    @Singleton
    fun provideAnswersAnalytics(): AnswersAnalytics {
        return AnswersAnalytics()
    }

    @Provides
    @Singleton
    fun provideGoogleAnalyticsForFirebase(context: Application): GoogleAnalyticsForFirebase {
        return GoogleAnalyticsForFirebase(context)
    }

    @Provides
    @Singleton
    fun provideAnalyticsManager(
        analyticsTrackingInteractor: AnalyticsTrackingInteractor,
        getCurrentServerInteractor: GetCurrentServerInteractor,
        getAccountsInteractor: GetAccountsInteractor,
        answersAnalytics: AnswersAnalytics,
        googleAnalyticsForFirebase: GoogleAnalyticsForFirebase
    ): AnalyticsManager {
        return AnalyticsManager(
            analyticsTrackingInteractor,
            getCurrentServerInteractor,
            getAccountsInteractor,
            listOf(answersAnalytics, googleAnalyticsForFirebase)
        )
    }
}
