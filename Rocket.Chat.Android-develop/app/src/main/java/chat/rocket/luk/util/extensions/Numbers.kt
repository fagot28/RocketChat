package chat.rocket.luk.util.extensions

import org.threeten.bp.LocalDateTime

fun Long?.localDateTime(): LocalDateTime? {
    return this?.let {
        DateTimeHelper.getLocalDateTime(it)
    }
}