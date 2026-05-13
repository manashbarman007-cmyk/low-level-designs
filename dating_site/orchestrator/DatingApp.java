package dating_site.orchestrator;

import dating_site.chat_room.ChatRoom;
import dating_site.match_score.MatchScore;
import dating_site.match_score_factory.MatcherType;
import dating_site.nearby_strategy.BasicNearbyStrategy;
import dating_site.nearby_strategy.NearbyStrategy;
import dating_site.notification_service.NotificationService;
import dating_site.repository.ChatRoomRepository;
import dating_site.repository.UserRepository;
import dating_site.swipe.Swipe;
import dating_site.user.Score;
import dating_site.user.User;
import dating_site.user.UserPreference;
import dating_site.user.UserProfile;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// singleton
public class DatingApp {

    public static volatile DatingApp datingApp;
    private final NotificationService notificationService;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final List<MatcherType> matcherTypeList;
    private final MatchScore matchScore;
    private NearbyStrategy nearbyStrategy;

    private DatingApp() {

        this.notificationService = NotificationService.getNotificationService();
        this.userRepository = UserRepository.getUserRepository();
        this.chatRoomRepository = ChatRoomRepository.getChatRoomRepository();
        this.matcherTypeList = new ArrayList<>();
        this.matchScore = new MatchScore();
        this.nearbyStrategy = new BasicNearbyStrategy();

    }

    public static DatingApp getDatingApp() {
        if (datingApp == null) { // first check
            synchronized (DatingApp.class) {
                if (datingApp == null) { // second check
                    datingApp = new DatingApp();
                }
            }
        }
        return datingApp;
    }

    // for runtime change
    public void setNearbyStrategy(NearbyStrategy nearbyStrategy) {
        this.nearbyStrategy = nearbyStrategy;
    }

    public void addMatcherType (MatcherType matcherType) {
        matcherTypeList.add(matcherType);
    }

    public List<User> findNearbyUsers (User currentUser) {
        Map<String, User> usersMap = userRepository.getUsersMap();
        List<User> allUsers = new ArrayList<>();
        for (Map.Entry<String, User> entry : usersMap.entrySet()) {
            allUsers.add(entry.getValue());
        }
        return nearbyStrategy.findNearbyUsers(currentUser, allUsers);
    }

    public double computeAndSaveMatchScore (User user, User targetUser) {
        double finalScore = matchScore.getFinalScore(user, targetUser, matcherTypeList);
        user.addScore(targetUser.getUserId(), finalScore);
        return finalScore;
    }

    private String generateChatRoomId(String userId1, String userId2) {
        return userId1.compareTo(userId2) < 0
                ? userId1 + "_" + userId2
                : userId2 + "_" + userId1;
    }

    public void createUser (String userId, UserPreference userPreference, UserProfile userProfile) {
        User user = new User(userId);
        user.setUserProfile(userProfile);
        user.setPreference(userPreference);
        userRepository.addUser(userId, user);
    }

    public void swipe (String userId, String targetId, Swipe swipe, boolean userWantsToChat) {

        if (userId.equals(targetId)) {
            throw new IllegalArgumentException("User cannot swipe themselves");
        }

        User user = userRepository.getUserById(userId);

        if (user.getSwipeHistory().containsKey(targetId)) {
            throw new IllegalArgumentException(
                    "User already swiped this profile.");
        }

        String chatRoomId = generateChatRoomId(userId, targetId);
        String userName = user.getUserProfile().getName();
        User targetUser = userRepository.getUserById(targetId);
        String targetUserName = targetUser.getUserProfile().getName();
        double score = computeAndSaveMatchScore(user, targetUser);
        user.swipe(targetId, swipe);

        if (swipe == Swipe.RIGHT) {
            if (userWantsToChat && targetUser.isInterestedIn(userId)) {
                notificationService.notifyUser(
                        userId,
                        userName + ", you matched with " + targetUserName + " with score : " + score);

                notificationService.notifyUser(
                        targetId,
                        targetUserName + ", you matched with " + userName + " with score : " + score);

                ChatRoom chatRoom;
                synchronized (this) {
                    if (!chatRoomRepository.chatRoomExists(chatRoomId)) {
                        chatRoom = setUpChatRoom(chatRoomId);
                    }else {
                        chatRoom = chatRoomRepository.getChatRoomById(chatRoomId);
                    }
                    chatRoom.addUserId(userId);
                    chatRoom.addUserId(targetId);
                }

            }
        }
    }

    public ChatRoom setUpChatRoom (String chatRoomId) {
        ChatRoom chatRoom = new ChatRoom(chatRoomId);
        chatRoomRepository.addChatRoom(chatRoomId, chatRoom);
        return chatRoom;
    }

    public List<User> topKRecommendations (int k, User user) {
        Map<String, Score> matchScoreValues = user.getMatchScoreValues();
        if (k > matchScoreValues.size()) {
            throw new IllegalArgumentException("Not enough matches.");
        }
        List<User> list = new ArrayList<>();

        // Convert map to List
        List<Map.Entry<String, Score>> entries = new ArrayList<>(matchScoreValues.entrySet());

        entries.sort((a, b) ->
                Double.compare(b.getValue().getScore(), a.getValue().getScore()));

        for (int i = 0; i < k; i++) {
            String targetId = entries.get(i).getKey();
            User targetUser = userRepository.getUserById(targetId);
            list.add(targetUser);
        }
        return list;
    }
}
