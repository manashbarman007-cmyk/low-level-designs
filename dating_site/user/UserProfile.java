package dating_site.user;

import dating_site.location_service.Location;

import java.util.*;

public class UserProfile {
    private String name;
    private int age;
    private Gender gender;
    private final Set<Interest> interests;
    private String bio;
    private Location location;
    private final List<String> pathToImages;

    public UserProfile(String name, int age) {
        this.name = name;

        if (age < 18) {
            throw new IllegalArgumentException("Age can not be lower than 18");
        }

        this.age = age;

        this.interests = new HashSet<>();
        this.pathToImages = new ArrayList<>();
    }

    public UserProfile(String name, int age, double latitude, double longitude) {

        this(name, age);

        this.location = new Location(latitude, longitude);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public Set<Interest> getInterests() {
        return Collections.unmodifiableSet(interests);
    }

    public void addInterest(Interest interest) {
        interests.add(interest);
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getPathToImages() {
        return Collections.unmodifiableList(pathToImages);
    }

    public void addPathToImages(String path) {
        pathToImages.add(path);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void displayProfile() {
        System.out.println("User name : " + this.name);
        System.out.println("User age : " + this.age);
        System.out.println("User gender : " + ((this.gender != null) ? this.gender.name() : "Not specified"));

        System.out.println("User interests: ");

        for (Interest interest : interests) {
            System.out.println("Interest name : " + interest.getName());
            System.out.println("Interest category : " + interest.getCategory());
        }

        System.out.println("User bio : " + this.bio);

        if (location != null) {
            System.out.println("User location : " + "(" + this.location.getLatitude() + ", "
                    + this.location.getLongitude() + ")");
        }

    }
}
