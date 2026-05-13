package dating_site.chat_room;

import dating_site.notification_service.NotificationService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ChatRoom {
    private final String id;
    private final Set<String> userIdList;
    private final List<Message> messages;
    private final NotificationService notificationService;

    public ChatRoom(String id) {
        this.id = id;
        this.userIdList = new HashSet<>();
        this.messages = new ArrayList<>();
        this.notificationService = NotificationService.getNotificationService();
    }

    public String getId() {
        return id;
    }

    public Set<String> getUserIdList() {
        return userIdList;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void showAllMessages () {
        for (Message message : messages) {
            System.out.println("Message ID : " + message.getId() + " , Sender ID : " + message.getSenderId() +
                    " , Sent at : " + message.getTime() + " , Content : " + message.getContent());
        }
    }

    public void sendMessage(String messageId ,String senderId, String content) {
        if (!userIdList.contains(senderId)) {
           throw new IllegalArgumentException("Sender does not belong to this chat room.");
        }
        Message message = new Message(messageId, senderId);
        message.setContent(content);
        addMessage(message);
        for (String targetId : userIdList) {
            if (!targetId.equals(senderId)) {
                notificationService.notifyUser(
                        targetId,
                        "New message from " + senderId
                );
            }
        }
    }

    public void addMessage (Message message) {
        messages.add(message);
    }

    public void deleteMessage (Message message) {
        messages.remove(message);
    }

    public void addUserId (String userId) {
        userIdList.add(userId);
    }

    public void deleteUserId (String userId) {
        userIdList.remove(userId);
    }
}
