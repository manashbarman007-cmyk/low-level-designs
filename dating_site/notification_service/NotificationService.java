package dating_site.notification_service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// singleton class
public class NotificationService {

    private static volatile NotificationService notificationService;
    private final Map<String, NotificationObserver> observers;

    // private constructor
    private NotificationService() {
        this.observers = new ConcurrentHashMap<>();
    }

    public static NotificationService getNotificationService() {
        if (notificationService == null) { // first check
            synchronized (NotificationService.class) {
                if (notificationService == null) { // second check
                    notificationService = new NotificationService();
                }
            }
        }
        return notificationService;
    }

    public void addObserver (String userId, NotificationObserver observer) {
        observers.put(userId, observer);
    }
    public void removeObserver (String userId) {
        observers.remove(userId);
    }
    public void notifyUser(String userId, String message) {
        NotificationObserver notificationObserver = observers.get(userId);
        if (notificationObserver == null) throw new IllegalArgumentException("No such user exists");
        notificationObserver.update(message);
    }

    public void notifyAllUsers(String message) {
        for (NotificationObserver notificationObserver : observers.values()) {
            notificationObserver.update(message);
        }
    }

}
