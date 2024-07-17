package Conway;

import java.util.*;

public class Conway {
    private boolean[][] cellsArray = new boolean[80][80];

    public Conway() {

    }

    public boolean[][] getCellsArray() {
        return cellsArray;
    }

    public void setCellsArray(boolean[][] cellsArray) {
        this.cellsArray = cellsArray;
    }

    public boolean[][] initalizeCellsArray() {
        // Initialize cell array in a random configuration
        Random rand = new Random();

        for (int i = 0; i < cellsArray.length; i++) {
            for (int j = 0; j < cellsArray[i].length; j++) {
                if(rand.nextInt(15) < 1) {
                    cellsArray[i][j] = true;
                }
            }
        }
        return cellsArray;
    }

    // Check neighbours according to game rules
    public void updateGameBoard() {
        boolean[][] cellsArrayCopy = cellsArray;
        // Custom Hasmap to save a custom object of type coordinate as its key and a placeholder value
        HashMap<Coordinate, Boolean> checkedCellsCoordinates = new HashMap<>();

        for (int i = 0; i < cellsArrayCopy.length; i++) {
            for (int j = 0; j < cellsArrayCopy[i].length; j++) {

                    // Create a new coordinate of object to save later
                    Coordinate coordinate = new Coordinate(i, j);

                    // If cell hasn't been checked yet (its coordinates aren't in the checked cells map)
                    if(!coordinate.isChecked(checkedCellsCoordinates, i, j)) {

                        // Save its coordinates
                        checkedCellsCoordinates.put(coordinate, true);

                        int liveCellCont = 0;
                        ArrayList<Boolean> neighbours;

                        neighbours = checkNeighbours(cellsArrayCopy, i, j);

                        // Count live neighbouring cells
                        for (Boolean neighbour : neighbours) {

                            if(neighbour) {
                                liveCellCont++;
                            }

                        }

                        // Any live cell with fewer than two live neighbors dies (underpopulation).
                        if (cellsArrayCopy[i][j] && liveCellCont < 2) {
                            cellsArrayCopy[i][j] = false;
                        }
                        // Any live cell with two or three live neighbors lives on to the next generation.
                        if (cellsArrayCopy[i][j] && (liveCellCont == 2 || liveCellCont == 3)) {
                            cellsArrayCopy[i][j] = true;
                        }
                        // Any live cell with more than three live neighbors dies (overpopulation).
                        if (cellsArrayCopy[i][j] && liveCellCont > 3) {
                            cellsArrayCopy[i][j] = false;
                        }
                        // Any dead cell with exactly three live neighbors becomes a live cell (reproduction).
                        if (!cellsArrayCopy[i][j] && liveCellCont == 3) {
                            cellsArrayCopy[i][j] = true;
                        }
                    }
            }
        }

        // Update game board once rules are checked
        cellsArray = cellsArrayCopy;
    }

    // Checks whether the cell has an out of boundaries position to be checked
    private boolean isValidPos(int i, int j) {
        return i >= 0 && j >= 0 && i < cellsArray.length && j < cellsArray[i].length;
    }

    private ArrayList<Boolean> checkNeighbours (boolean[][] cellsArray, int i, int j) {

        ArrayList<Boolean> neighbours = new ArrayList<>();

        // Checking for all the possible adjacent positions

        // Checks top left corner neighbour
        if (isValidPos(i - 1, j - 1)) {
            neighbours.add(cellsArray[i - 1][j - 1]);
        }
        // Checks top neighbour
        if (isValidPos(i - 1, j)) {
            neighbours.add(cellsArray[i - 1][j]);
        }
        // Checks top right corner neighbour
        if (isValidPos(i - 1, j + 1)) {
            neighbours.add(cellsArray[i - 1][j + 1]);
        }
        // Checks left neighbour
        if (isValidPos(i, j - 1)) {
            neighbours.add(cellsArray[i][j - 1]);
        }
        // Checks right neighbour
        if (isValidPos(i, j + 1)) {
            neighbours.add(cellsArray[i][j + 1]);
        }
        // Checks lower left corner neighbour
        if (isValidPos(i + 1, j - 1)) {
            neighbours.add(cellsArray[i + 1][j - 1]);
        }
        // Checks bottom neighbour
        if (isValidPos(i + 1, j)) {
            neighbours.add(cellsArray[i + 1][j]);
        }
        // Checks lower right neighbour
        if (isValidPos(i + 1, j + 1)) {
            neighbours.add(cellsArray[i + 1][j + 1]);
        }

        return neighbours;
    }

}