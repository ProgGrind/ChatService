import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun createChat() {
        //arrange
        val service = ChatService()

        //act
        val chat = service.createChat(1, 2)

        //arrange
        assertEquals(chat.chatId, 1)
    }

    @Test
    fun noCreateChat() {
        //arrange
        val service = ChatService()

        //act
        val chat = service.createChat(1, 2)

        //arrange
        assertNotEquals(chat.chatId, 5)
    }

    @Test
    fun deleteChat() {
        //arrange
        val service = ChatService()

        //act
        service.createChat(1, 2)
        service.deleteChat(1)

        //arrange
        assertEquals("No messages in any chat", service.getChats())
    }

    @Test
    fun noDeleteChat() {
        //arrange
        val service = ChatService()

        //act
        service.createChat(1, 2)
        service.sendMessage(1,2)
        service.deleteChat(3)

        //arrange
        assertNotEquals("No messages in any chat", service.getChats())
    }

    @Test
    fun sendMessage() {
        //arrange
        val service = ChatService()

        //act
        val chat = service.createChat(1, 2)
        service.sendMessage(1,2)

        //arrange
        assertEquals(chat.messages.last().messageId, 1)
    }

    @Test
    fun noSendMessage() {
        //arrange
        val service = ChatService()

        //act
        val chat = service.createChat(1, 2)
        service.sendMessage(1,2)

        //arrange
        assertNotEquals(chat.messages.last().messageId, 3)
    }

    @Test
    fun deleteMessage() {
        //arrange
        val service = ChatService()

        //act
        val chat = service.createChat(1, 2)
        service.sendMessage(1,2)
        service.sendMessage(1,2)
        service.deleteMessage(1,2)

        //arrange
        assertNotEquals(chat.messages.last().messageId, 2)
    }

    @Test
    fun noDeleteMessage() {
        //arrange
        val service = ChatService()

        //act
        val chat = service.createChat(1, 2)
        service.sendMessage(1,2)
        service.sendMessage(1,2)
        service.deleteMessage(2,5)

        //arrange
        assertEquals(chat.messages.last().messageId, 2)
    }

    @Test
    fun getUnreadChatsCount() {
        //arrange
        val service = ChatService()

        //act
        service.createChat(1, 2)
        service.sendMessage(1,2)
        service.sendMessage(1,2)
        service.createChat(1, 3)
        service.sendMessage(1,3)
        service.sendMessage(1,3)
        val result = service.getUnreadChatsCount()

        //arrange
        assertEquals(result, 2)
    }

    @Test
    fun noGetUnreadChatsCount() {
        //arrange
        val service = ChatService()

        //act
        service.createChat(1, 2)
        service.sendMessage(1,2)
        service.sendMessage(1,2)
        val result = service.getUnreadChatsCount()

        //arrange
        assertNotEquals(result, 2)
    }

    @Test
    fun getMessageList() {
        //arrange
        val service = ChatService()

        //act
        service.createChat(1, 2)
        service.sendMessage(1,2)
        service.sendMessage(1,2)
        val result = service.getMessageList(1,1,2)
        //arrange
        assertEquals(result!![0].messageId, 1)
    }

    @Test
    fun noGetMessageList() {
        //arrange
        val service = ChatService()

        //act
        service.createChat(1, 2)
        service.sendMessage(1,2)
        service.sendMessage(1,2)
        val result = service.getMessageList(1,1,2)

        //arrange
        assertNotEquals(result!![0].messageId, 3)
    }
}


