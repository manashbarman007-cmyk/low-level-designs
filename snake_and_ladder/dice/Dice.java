package snake_and_ladder.dice;

import java.util.Random;

public class Dice {
    private final int face;

    public Dice (int face) {
        this.face = face;
    }

    public int getFace() {
        return face;
    }

    public int rollDice () {
        Random random = new Random();
        return random.nextInt(1, face + 1);
    }
}
