package model;

/**
 * @author Elena Kurilina
 */
public class Move {

    public final Cell cell;

    public final CellState player;

    public Move(Cell cell, CellState player) {
        this.cell = cell;
        this.player = player;
    }

    public Move(int row, int column, CellState player) {
        this.cell = new Cell(row, column);
        this.player = player;
    }

    @Override
    public String toString() {
        return "Move{" +
                "cell=" + cell +
                ", player=" + player +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (cell != null ? !cell.equals(move.cell) : move.cell != null) return false;
        if (player != move.player) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cell != null ? cell.hashCode() : 0;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        return result;
    }
}
