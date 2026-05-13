package snake_and_ladder.boardentity;

public class Snake extends BoardEntity{

    public Snake(int start, int end) {
        super(start, end);
        if (end >= start) {
            throw new IllegalArgumentException("End must be smaller");
        }
    }

    @Override
    public void display() {
        System.out.println("Snake start : " + super.getStart() + ", Snake end : " + super.getEnd());
    }

    @Override
    public String name() {
        return "Snake";
    }
}
