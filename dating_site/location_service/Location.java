package dating_site.location_service;

import java.util.Objects;

public class Location {
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    // Haversine Formula
    public double distanceInKm(Location targetLocation) {

        double otherLatitude = targetLocation.getLatitude();
        double otherLongitude = targetLocation.getLongitude();

        // convert degrees to radians
        double lat1 = Math.toRadians(this.latitude);
        double lon1 = Math.toRadians(this.longitude);

        double lat2 = Math.toRadians(otherLatitude);
        double lon2 = Math.toRadians(otherLongitude);

        // differences
        double deltaLat = lat2 - lat1;
        double deltaLon = lon2 - lon1;

        // Haversine formula
        double a =
                Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                        +
                        Math.cos(lat1) * Math.cos(lat2)
                                *
                                Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Earth radius in km
        double earthRadius = 6371;

        return earthRadius * c;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Location location)) return false;
        return Double.compare(latitude, location.latitude) == 0 && Double.compare(longitude, location.longitude) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
