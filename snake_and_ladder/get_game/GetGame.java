package snake_and_ladder.get_game;

import snake_and_ladder.game.Game;
import snake_and_ladder.game_factory.SnakeAndLadderGameFactory;
import snake_and_ladder.pair.Pair;
import snake_and_ladder.setup.Difficulty;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetGame {
    private final Scanner scanner;
    private final List<Pair> snakePositions;
    private final List<Pair> ladderPositions;
    private final SnakeAndLadderGameFactory factory;

    public GetGame() {
        this.snakePositions = new ArrayList<>();
        this.ladderPositions = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.factory = new SnakeAndLadderGameFactory();
    }

    public Game getGame() {

        System.out.println("Choose game type : Standard, Custom, Random");
        String gameType = scanner.nextLine().trim().toUpperCase();
        Game game;
        switch (gameType) {
            case "STANDARD" -> game = factory.standardSnakeAndLadderGame();
            case "CUSTOM" -> {
                snakePositions.clear();
                ladderPositions.clear();
                System.out.println("Enter board dimension : (e.g. : 10)");
                int dimension = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter dice faces : (e.g. : 6)");
                int faces = scanner.nextInt();
                scanner.nextLine();
                System.out.println("""
                        Do you want random positions ? If yes, enter "true" else enter "false"
                        """);
                boolean randomPosition = scanner.nextBoolean();
                scanner.nextLine();
                if (randomPosition) {
                    game = factory.customSnakeAndLadderGame(dimension, faces,  -1, -1, snakePositions, ladderPositions, true);
                }else {
                    System.out.println("Enter number of snakes:");
                    int numOfSnakes = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter number of ladders:");
                    int numOfLadders = scanner.nextInt();
                    scanner.nextLine();
                    // add snakes
                    while (snakePositions.size() < numOfSnakes) {
                        System.out.println("Enter head of snake : ");
                        int start = scanner.nextInt();
                        System.out.println("Enter tail of snake : ");
                        int end = scanner.nextInt();

                        if (start <= end) {
                            throw new IllegalArgumentException("head of snake must be higher");
                        }

                        snakePositions.add(new Pair(start, end));
                    }

                    // add ladders
                    while (ladderPositions.size() < numOfLadders) {
                        System.out.println("Enter bottom of ladder : ");
                        int start = scanner.nextInt();
                        System.out.println("Enter top of ladder : ");
                        int end = scanner.nextInt();
                        if (start >= end) {
                            throw new IllegalArgumentException("bottom of ladder must be lower");
                        }
                        ladderPositions.add(new Pair(start, end));
                    }

                    game = factory.customSnakeAndLadderGame(dimension, faces, numOfSnakes, numOfLadders, snakePositions, ladderPositions,false);

                }
            }
            case "RANDOM" -> {
                System.out.println("Enter board dimension : (e.g. : 10)");
                int dimension = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter dice faces : (e.g. : 6)");
                int faces = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Choose difficulty level : EASY, MEDIUM, HARD");
                String difficultyString = scanner.nextLine().trim().toUpperCase();
                Difficulty difficulty = Difficulty.valueOf(difficultyString);
                game = factory.randomSnakeAndLadderGame(dimension, difficulty, faces);
            }
            default -> throw new IllegalArgumentException("Invalid game type");
        }
        return game;
    }
}
