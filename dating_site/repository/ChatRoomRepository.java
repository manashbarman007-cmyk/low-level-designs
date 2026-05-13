package dating_site.repository;

import dating_site.chat_room.ChatRoom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// singleton
public class ChatRoomRepository {
    private final Map<String, ChatRoom> chatRoomsMap;
    private static volatile ChatRoomRepository chatRoomRepository;
    private ChatRoomRepository() {
        this.chatRoomsMap = new ConcurrentHashMap<>();
    }

    public static ChatRoomRepository getChatRoomRepository() {
        if (chatRoomRepository == null) { // first check
            synchronized (ChatRoomRepository.class) {
                if (chatRoomRepository == null) {
                    chatRoomRepository = new ChatRoomRepository();
                }
            }
        }
        return chatRoomRepository;
    }

    public void addChatRoom (String chatRoomId, ChatRoom chatRoom) {
        chatRoomsMap.put(chatRoomId, chatRoom);
    }

    public ChatRoom getChatRoomById (String chatRoomId) {
        if (chatRoomsMap.containsKey(chatRoomId)) {
            return chatRoomsMap.get(chatRoomId);
        }
        else {
            throw new IllegalArgumentException("No such Chat Room.");
        }
    }

    public boolean chatRoomExists(String chatRoomId) {
        return chatRoomsMap.containsKey(chatRoomId);
    }
}
