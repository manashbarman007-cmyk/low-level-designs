package dating_site.match_score;

import dating_site.user.User;

public abstract class MatchScoreHandler {
    protected MatchScoreHandler next;

    public MatchScoreHandler setNext (MatchScoreHandler next) {
        this.next = next;
        return next;
    }

    public abstract int calculateScore (User user1, User user2);
}
