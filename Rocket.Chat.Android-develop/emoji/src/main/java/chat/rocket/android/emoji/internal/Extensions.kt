package chat.rocket.luk.emoji.internal

import chat.rocket.luk.emoji.Emoji

fun Emoji.isCustom(): Boolean = this.url != null
