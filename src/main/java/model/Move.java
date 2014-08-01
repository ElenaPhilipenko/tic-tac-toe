package model;

/**
 * @author Elena Kurilina
 */
public class Move {

    public final int column;

    public final int row;

    public final CellState player;

    public Move(int column, int row, CellState player) {
        this.column = column;
        this.row = row;
        this.player = player;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Move move = (Move) o;

        if (column != move.column) return false;
        if (row != move.row) return false;
        if (player != move.player) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = column;
        result = 31 * result + row;
        result = 31 * result + (player != null ? player.hashCode() : 0);
        return result;
    }
}
