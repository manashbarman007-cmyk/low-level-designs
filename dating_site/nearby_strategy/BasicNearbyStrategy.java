package dating_site.nearby_strategy;


import dating_site.location_service.Location;
import dating_site.user.Gender;
import dating_site.user.User;
import dating_site.user.UserPreference;

import java.util.List;


public class BasicNearbyStrategy implements NearbyStrategy{

    @Override
    public List<User> findNearbyUsers(User currentUser, List<User> allUsers) {

        UserPreference curUserPreference = currentUser.getPreference();
        List<Gender> curUserPreferredGenders = curUserPreference.getGenders();
        double maxDistance = curUserPreference.getMaxDistance();
        Location curUserLocation = currentUser.getUserProfile().getLocation();
        int minAge = curUserPreference.getMinAge();
        int maxAge = curUserPreference.getMaxAge();

        return allUsers.stream()
                // avoid self matching
                .filter(
                        user -> !user.equals(currentUser)
                )
                // preferred gender
                .filter( user ->
                        {
                            Gender gender = user.getUserProfile().getGender();
                            return curUserPreferredGenders.contains(gender);
                        }
                )
                // distance filter
                .filter( user ->
                       {
                           Location otherUserLocation = user.getUserProfile().getLocation();
                           double distance = curUserLocation.distanceInKm(otherUserLocation);
                           return distance <= maxDistance;
                       }

                )
                // age filter
                .filter(
                        user ->
                        {
                            int age = user.getUserProfile().getAge();
                            return age >= minAge && age <= maxAge;
                        }

                )
                .toList();
    }
}
