class Chat (
    val chatId: Int = 0,
    val userId: Int,
    val toUserId: Int,
    val messages: MutableList<Message> = mutableListOf()
)