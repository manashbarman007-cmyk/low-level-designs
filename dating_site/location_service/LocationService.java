package dating_site.location_service;

import dating_site.nearby_strategy.NearbyStrategy;
import dating_site.user.User;

import java.util.List;

public class LocationService {
    private static volatile LocationService locationService;
    private NearbyStrategy nearbyStrategy;

    private LocationService() {}

    public static LocationService getLocationService() {
        if (locationService == null) { // first check
            synchronized (LocationService.class) {
                if (locationService == null) { // second check
                    locationService = new LocationService();
                }
            }
        }
        return locationService;
    }

    public void setNearbyStrategy(NearbyStrategy nearbyStrategy) {
        this.nearbyStrategy = nearbyStrategy;
    }

    public NearbyStrategy getNearbyStrategy() {
        return this.nearbyStrategy;
    }

    public List<User> findNearbyUser(User currentUser, List<User> allUsers) {
        return nearbyStrategy.findNearbyUsers(currentUser, allUsers); // delegating it to NearbyStrategy
    }
}
