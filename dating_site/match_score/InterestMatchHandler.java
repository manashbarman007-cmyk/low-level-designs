package dating_site.match_score;

import dating_site.user.Interest;
import dating_site.user.User;
import java.util.Set;

public class InterestMatchHandler extends MatchScoreHandler{
    @Override
    public int calculateScore(User user1, User user2) {
        int score = 0;
        Set<Interest> user1Interests = user1.getUserProfile().getInterests();
        Set<Interest> user2Interests = user2.getUserProfile().getInterests();
        for (Interest interest : user1Interests) {
            if (user2Interests.contains(interest)) {
                score += 10;
            }
        }
        if (super.next != null) { // pass the request to next handler
            score += next.calculateScore(user1, user2);
        }
        return score;
    }
}
