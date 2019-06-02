package chat.rocket.luk.util.extensions

import android.content.Context
import org.threeten.bp.LocalDateTime

fun LocalDateTime?.date(context: Context): String? {
    return this?.let {
        DateTimeHelper.getDate(it, context)
    }
}