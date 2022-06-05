class Message (
    val messageId: Int = 0,
    val text: String = "",
    val chatId: Int,
    var isRead: Boolean = false
)