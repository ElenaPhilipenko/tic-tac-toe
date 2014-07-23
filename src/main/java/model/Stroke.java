package model;

/**
 * @author Elena Kurilina
 */
public class Stroke {

    public final int column;

    public final int row;

    public final CellState state;

    public Stroke(int column, int row, CellState state) {
        this.column = column;
        this.row = row;
        this.state = state;
    }
}
