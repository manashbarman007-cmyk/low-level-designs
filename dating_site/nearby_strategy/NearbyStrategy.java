package dating_site.nearby_strategy;

import dating_site.location_service.Location;
import dating_site.user.Gender;
import dating_site.user.User;

import java.util.List;

public interface NearbyStrategy {
    List<User> findNearbyUsers (User currentUser, List<User> allUsers);
}
