package dating_site.match_score;

import dating_site.location_service.Location;
import dating_site.user.User;

public class LocationMatchHandler extends MatchScoreHandler{


    @Override
    public int calculateScore(User user1, User user2) {
        int score = 0;
        Location user1Loc = user1.getUserProfile().getLocation();
        Location user2Loc = user2.getUserProfile().getLocation();

        double dist = user1Loc.distanceInKm(user2Loc);

        double user1MaxDistance = user1.getPreference().getMaxDistance();
        double user2MaxDistance = user2.getPreference().getMaxDistance();

        if (dist <= user1MaxDistance && dist <= user2MaxDistance) {
            score = 50;
        }
        if (super.next != null) { // pass the request to next handler
            score += next.calculateScore(user1, user2);
        }
        return score;
    }
}
