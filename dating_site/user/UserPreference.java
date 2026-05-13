package dating_site.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserPreference {
    private int minAge;
    private int maxAge;
    private double maxDistance;
    private final List<Interest> interests;
    private final List<Gender> genders;

    public UserPreference(int minAge, int maxAge, double maxDistance) {

        if (minAge < 18) {
            throw new IllegalArgumentException("Age can not be lower than 18");
        }
        if (maxAge < minAge) {
            throw new IllegalArgumentException(
                    "Max age must be greater than min age");
        }
        if (maxDistance < 0) {
            throw new IllegalArgumentException("Distance can not be negative");
        }
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.maxDistance = maxDistance;
        this.interests = new ArrayList<>();
        this.genders = new ArrayList<>();

    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        if (minAge < 18) {
            throw new IllegalArgumentException("Age can not be lower than 18");
        }
        if (minAge > this.maxAge) {
            throw new IllegalArgumentException(
                    "Min age must be smaller than max age");
        }
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        if (maxAge < this.minAge) {
            throw new IllegalArgumentException(
                    "Max age must be greater than min age");
        }
        this.maxAge = maxAge;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        if (maxDistance < 0) {
            throw new IllegalArgumentException("Distance can not be negative");
        }
        this.maxDistance = maxDistance;
    }

    public List<Interest> getInterests() {
        return Collections.unmodifiableList(interests);
    }

    public List<Gender> getGenders() {
        return Collections.unmodifiableList(genders);
    }

    public void addInterest (Interest interest) {
        if (interests.size() < 5) {
            interests.add(interest);
        }else {
            throw new IllegalStateException("Maximum 5 interests allowed");
        }
    }

    public void addGender (Gender gender) {
        genders.add(gender);
    }
}
