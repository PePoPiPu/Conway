package Conway;

import java.util.HashMap;
import java.util.Objects;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public HashMap<Coordinate, Boolean> saveCellCoordinates (HashMap<Coordinate, Boolean> map, int i, int j) {
        // Put coordinates into the map
        map.put(new Coordinate(i, j), true);
        // Return updated map
        return map;
    }

    public boolean isChecked (HashMap<Coordinate, Boolean> map, int i, int j) {

        // Create new Coordinate object with coordinates i, j
        Coordinate coordinateToCheck = new Coordinate(i, j);

        // Return true if it exists in the map
        return map.containsKey(coordinateToCheck);
    }
}
