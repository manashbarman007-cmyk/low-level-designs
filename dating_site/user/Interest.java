package dating_site.user;

import java.util.Objects;

public class Interest {
    private String name;
    private String category;

    public Interest(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Interest interest)) return false;
        return Objects.equals(name, interest.name) && Objects.equals(category, interest.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }
}
