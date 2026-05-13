package snake_and_ladder.boardentity;

public class Ladder extends BoardEntity{
    public Ladder(int start, int end) {
        super(start, end);
        if (end <= start) {
            throw new IllegalArgumentException("End must be larger");
        }
    }

    @Override
    public void display() {
        System.out.println("Ladder start : " + super.getStart() + ", Ladder end : " + super.getEnd());
    }

    @Override
    public String name() {
        return "Ladder";
    }
}
