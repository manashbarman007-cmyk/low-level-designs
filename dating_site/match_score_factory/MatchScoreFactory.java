package dating_site.match_score_factory;

import dating_site.match_score.AgeMatchHandler;
import dating_site.match_score.InterestMatchHandler;
import dating_site.match_score.LocationMatchHandler;
import dating_site.match_score.MatchScoreHandler;

import java.util.List;

public class MatchScoreFactory {
    public MatchScoreHandler getMatchScoreHandler (MatcherType matcherType) {
        switch (matcherType) {
            case AGE -> {
                return new AgeMatchHandler();
            }
            case INTEREST -> {
                return new InterestMatchHandler();
            }
            case LOCATION -> {
                return new LocationMatchHandler();
            }
            default -> throw new IllegalArgumentException("No such type.");
        }
    }

    // similar to linked list concept
    public MatchScoreHandler buildChain (List<MatcherType> matcherTypeList) {

        int n = matcherTypeList.size();
        if (n == 0) {
            throw new IllegalArgumentException("No MatchType available.");
        }
        MatchScoreHandler head = null;
        MatchScoreHandler current = null;

        for (MatcherType type : matcherTypeList) {
            MatchScoreHandler matchScoreHandler = getMatchScoreHandler(type);

            // first matchScoreHandler becomes head
            if (head == null) {
                head = matchScoreHandler;
                current = matchScoreHandler;
            }else { // if (head != null)

                // matchScoreHandler becomes the next of current
                current.setNext(matchScoreHandler);

                // the current becomes the next matchScoreHandler
                current = matchScoreHandler;
            }
        }
        return head;
    }
}
