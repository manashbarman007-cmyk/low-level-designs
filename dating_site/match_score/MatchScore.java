package dating_site.match_score;

import dating_site.match_score_factory.MatchScoreFactory;
import dating_site.match_score_factory.MatcherType;
import dating_site.user.User;

import java.util.List;

public class MatchScore {

    private final MatchScoreFactory factory;

    public MatchScore() {
        this.factory = new MatchScoreFactory();
    }


    public double getFinalScore (User user1, User user2, List<MatcherType> matcherTypeList) {

        MatchScoreHandler matchScoreHandler = factory.buildChain(matcherTypeList);
        int MAX_SCORE = getMaxScore(matcherTypeList);
        int totalScore = matchScoreHandler.calculateScore(user1, user2);

        return ((double)totalScore / MAX_SCORE) * 100;
    }

    public int getMaxScore (List<MatcherType> matcherTypeList) {
        int MAX_SCORE = 0;

        for (int i = 0; i < matcherTypeList.size(); i++) {
            MAX_SCORE += 50;
        }

        return MAX_SCORE;
    }

}
