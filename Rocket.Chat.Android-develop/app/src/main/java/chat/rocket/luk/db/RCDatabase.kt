package chat.rocket.luk.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import chat.rocket.luk.db.model.AttachmentActionEntity
import chat.rocket.luk.db.model.AttachmentEntity
import chat.rocket.luk.db.model.AttachmentFieldEntity
import chat.rocket.luk.db.model.ChatRoomEntity
import chat.rocket.luk.db.model.MessageChannels
import chat.rocket.luk.db.model.MessageEntity
import chat.rocket.luk.db.model.MessageFavoritesRelation
import chat.rocket.luk.db.model.MessageMentionsRelation
import chat.rocket.luk.db.model.MessagesSync
import chat.rocket.luk.db.model.ReactionEntity
import chat.rocket.luk.db.model.UrlEntity
import chat.rocket.luk.db.model.UserEntity
import chat.rocket.luk.emoji.internal.db.StringListConverter

@Database(
    entities = [
        UserEntity::class,
        ChatRoomEntity::class,
        MessageEntity::class,
        MessageFavoritesRelation::class,
        MessageMentionsRelation::class,
        MessageChannels::class,
        AttachmentEntity::class,
        AttachmentFieldEntity::class,
        AttachmentActionEntity::class,
        UrlEntity::class,
        ReactionEntity::class,
        MessagesSync::class
    ],
    version = 13,
    exportSchema = true
)
@TypeConverters(StringListConverter::class)
abstract class RCDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun chatRoomDao(): ChatRoomDao
    abstract fun messageDao(): MessageDao
}
