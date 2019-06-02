package chat.rocket.luk.chatdetails.domain

data class Option(
    val name: String,
    val icon: Int,
    val listener: () -> Unit
)