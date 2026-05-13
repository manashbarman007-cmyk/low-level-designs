package snake_and_ladder.player;

public class Player {
    private final int id;
    private final String name;
    private int pos;
    private int score;
    private boolean canPlayerStart;
    private int maxCounter;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.pos = 0;
        this.score = 0;
        this.canPlayerStart = false;
        this.maxCounter = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPos() {
        return pos;
    }

    public boolean getCanPlayerStart() {
        return canPlayerStart;
    }

    public int getMaxCounter() {
        return maxCounter;
    }

    public void setMaxCounter(int maxCounter) {
        this.maxCounter = maxCounter;
    }

    public void setCanPlayerStart(boolean canPlayerStart) {
        this.canPlayerStart = canPlayerStart;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

}
