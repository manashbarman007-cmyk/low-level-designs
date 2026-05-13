package dating_site.chat_room;

import java.time.LocalDateTime;

public class Message {
    private final String id;
    private final String senderId;
    private final LocalDateTime time;
    private String content;

    public Message(String id, String senderId) {
        this.id = id;
        this.senderId = senderId;
        this.time = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getSenderId() {
        return senderId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
