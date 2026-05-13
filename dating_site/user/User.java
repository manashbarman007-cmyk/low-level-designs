package dating_site.user;

import dating_site.swipe.Swipe;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class User {
    private final String userId;
    private UserPreference preference;
    private UserProfile userProfile;
    private final Map<String, Swipe> swipeHistory; // will store string of targetUserID and the swipe
    private final Map<String, Score> matchScoreValues; // will store string of targetUserID and the matchScore

    public User(String userId) {
        this.userId = userId;
        this.swipeHistory = new ConcurrentHashMap<>();
        this.matchScoreValues = new ConcurrentHashMap<>();
    }

    public void addScore (String targetId, double score) {
        matchScoreValues.put(targetId, new Score(score));
    }

    public void swipe(String targetId, Swipe swipe) {
        swipeHistory.put(targetId, swipe);
    }

    public boolean isInterestedIn(String targetId) {
        return swipeHistory.containsKey(targetId) && swipeHistory.get(targetId) == Swipe.RIGHT;
    }

    public boolean isNotInterestedIn (String targetId){
        return swipeHistory.containsKey(targetId) && swipeHistory.get(targetId) == Swipe.LEFT;
    }

    public boolean hasInteractedWith (String targetId){
        return swipeHistory.containsKey(targetId);
    }


    public String getUserId() {
        return userId;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    public UserPreference getPreference() {
        return preference;
    }

    public void setPreference(UserPreference preference) {
        this.preference = preference;
    }

    public Map<String, Swipe> getSwipeHistory() {
        return Collections.unmodifiableMap(swipeHistory);
    }

    public Map<String, Score> getMatchScoreValues() {
        return Collections.unmodifiableMap(matchScoreValues);
    }

    public void displayUser () {
        this.userProfile.displayProfile();
    }
}
