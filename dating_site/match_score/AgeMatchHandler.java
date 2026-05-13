package dating_site.match_score;

import dating_site.user.User;

public class AgeMatchHandler extends MatchScoreHandler{
    @Override
    public int calculateScore(User user1, User user2) {
        int score = 0;
        int user1Age = user1.getUserProfile().getAge();
        int user2Age = user2.getUserProfile().getAge();
        int user1MinAgePref = user1.getPreference().getMinAge();
        int user1MaxAgePref = user1.getPreference().getMaxAge();
        int user2MinAgePref = user2.getPreference().getMinAge();
        int user2MaxAgePref = user2.getPreference().getMaxAge();

        if (user1Age >= user2MinAgePref && user1Age <= user2MaxAgePref
                && user2Age >= user1MinAgePref && user2Age <= user1MaxAgePref) {
            score += 50;
        }

        if (super.next != null) { // pass it to the next handler
            score += next.calculateScore(user1, user2);
        }
        return score;
    }
}
