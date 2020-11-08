package life;

import java.util.Arrays;
import java.util.Random;

public class Universe {
    private final int fieldSize;
    private String[][] field;
    private static final String ALIVE_CELL_SYMBOL = "O";
    private static final String DEAD_CELL_SYMBOL = " ";

    public Universe(int fieldSize) {
        this.fieldSize = fieldSize;
        field = new String[fieldSize][fieldSize];
    }

    public void initUniverse(long seed) {
        Random random = new Random(seed);
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {

                boolean cellValue = random.nextBoolean();
                field[i][j] = cellValue ? ALIVE_CELL_SYMBOL : DEAD_CELL_SYMBOL;
            }
        }
    }

    public void printUniverse() {
        for (String[] strings : field) {
            Arrays.stream(strings).forEach(System.out::print);
            System.out.println();
        }
    }

    public void calculateGeneration() {
        String[][] newField = new String[fieldSize][fieldSize];
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                int neighboursCount = getCellNeighboursCount(i, j);
                newField[i][j] = calculateCellState(neighboursCount, isAliveCell(i, j));
            }
        }
        field = newField;
    }

    private boolean isAliveCell(int i, int j) {
        return field[i][j].equals(ALIVE_CELL_SYMBOL);
    }

    private String calculateCellState(int neighboursCount, boolean isAliveCell) {
        if (isAliveCell && (neighboursCount != 2 && neighboursCount != 3)) {
            return DEAD_CELL_SYMBOL;
        } else if (!isAliveCell && neighboursCount == 3) {
            return ALIVE_CELL_SYMBOL;
        } else if (isAliveCell){
            return ALIVE_CELL_SYMBOL;
        } else {
            return DEAD_CELL_SYMBOL;
        }
    }

    private int getCellNeighboursCount(int cellX, int cellY) {
        int neighboursCount = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {

                if (i == 0 && j == 0) {
                    continue;
                }

                int shiftedX = getShiftedCoordinate(cellX, j);
                int shiftedY = getShiftedCoordinate(cellY, i);
                if (isAliveCell(shiftedX, shiftedY)) {
                    neighboursCount++;
                }
            }
        }

        return neighboursCount;
    }

    private int getShiftedCoordinate(int originalCoordinate, int shift) {
        int shiftedCoordinate = originalCoordinate + shift;
        if (shiftedCoordinate < 0) {
            return fieldSize - 1;
        } else if (shiftedCoordinate > fieldSize - 1) {
            return 0;
        } else {
            return shiftedCoordinate;
        }
    }
}
