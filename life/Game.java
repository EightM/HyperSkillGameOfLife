package life;

public class Game {
    private final Universe universe;
    private final int generationsCount;

    public Game(int fieldSize, long seed, int generationsCount) {
        this.universe = new Universe(fieldSize);
        this.generationsCount = generationsCount;
        universe.initUniverse(seed);
    }

    public void printState() {
        universe.printUniverse();
    }

    public void startGame() {
        for (int i = 0; i < generationsCount; i++) {
            universe.calculateGeneration();
        }
    }
}
