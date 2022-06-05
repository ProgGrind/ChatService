class ChatService {
    private val chats = mutableListOf<Chat>()
    private val deleting = mutableListOf<Chat>()
    private var createdChatId = 0
    private var createdMessageId = 0
//    private var writingUserId = 0


    fun createChat(writingUserId: Int, toUserId: Int): Chat {
        createdChatId++
        chats.add(Chat(createdChatId, writingUserId, toUserId))
        return chats.last()
    }

    fun deleteChat(chatId: Int) {
       chats.removeIf { it.chatId == chatId }
    }

    fun sendMessage(writingUserId: Int, toUserId: Int) {
        chats.filter { it.userId == writingUserId }
        chats.filter { it.toUserId == toUserId }
        createdMessageId++
        for (chat in chats) {
            chat.messages.add(Message(createdMessageId, chatId = chat.chatId, isRead = false))
        }
    }

    fun deleteMessage(chatId: Int, messageId: Int) {
        val chatList = chats.find { it.chatId == chatId }
        chats.removeIf { it.messages.last().messageId == messageId }
        chatList?.messages?.removeIf { it.messageId == messageId }
    }

    fun getUnreadChatsCount(): Int {
       return chats.filter {
            it.messages.any { it -> !it.isRead }
        }.size
    }

    fun getChats(): String {
        val chatList = chats.filter { it.messages.isNotEmpty() }
        return if (chatList.isNotEmpty()) {
            "List of chats: $chatList"
        } else "No messages in any chat"
    }

    fun getMessageById(chatId: Int, messageId: Int): Message? {
        val chatList = chats.find { it.chatId == chatId }
        return chatList?.messages?.find { it.messageId == messageId }
    }

    fun getMessageList(chatId: Int, messageId: Int, messageCount: Int): List<Message>? {
        val chat = chats.find { it.chatId == chatId }
        val messageList = chat?.messages
            ?.takeWhile { it.messageId == messageId }
            ?.take (messageCount)
        messageList?.forEach { it.isRead = true }
        return messageList
    }
}