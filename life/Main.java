package life;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int fieldSize = scanner.nextInt();
        int seed = scanner.nextInt();
        int generationsCount = scanner.nextInt();

        Game game = new Game(fieldSize, seed, generationsCount);
        game.startGame();
        game.printState();
    }
}
