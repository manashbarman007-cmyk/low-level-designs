package dating_site.repository;

import dating_site.user.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// singleton
public class UserRepository {

    private final Map<String, User> usersMap;
    private static volatile UserRepository userRepository;

    private UserRepository() {
        this.usersMap = new ConcurrentHashMap<>();
    }

    public static UserRepository getUserRepository() {
        if (userRepository == null) { // first check
            synchronized (UserRepository.class) {
                if (userRepository == null) {
                    userRepository = new UserRepository();
                }
            }
        }
        return userRepository;
    }

    public void addUser (String userId, User user) {
        usersMap.put(userId, user);
    }

    public User getUserById (String userId) {
        if (usersMap.containsKey(userId)) {
            return usersMap.get(userId);
        }
        else {
            throw new IllegalArgumentException("No such user.");
        }
    }

    public Map<String, User> getUsersMap() {
        return usersMap;
    }
}
