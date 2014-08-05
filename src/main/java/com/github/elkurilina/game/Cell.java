package com.github.elkurilina.game;

/**
 * @author Elena Kurilina
 */
public class Cell {
    public final int column;
    public final int row;

    public Cell(int row, int column) {
        this.column = column;
        this.row = row;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "column=" + column +
                ", row=" + row +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (column != cell.column) return false;
        if (row != cell.row) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        return result;
    }
}
