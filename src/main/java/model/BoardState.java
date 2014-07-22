package model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elena Kurilina
 */
public class BoardState {

    public final ArrayList<List<CellState>> rows;

    public BoardState(int size) {
        this.rows = new ArrayList<List<CellState>>(size);

        for (int i = 0; i < size; i++) {
            this.rows.add(createListOf(CellState.EMPTY, size));
        }
    }

    public int getSize() {
        return rows.size();
    }

    private List<CellState> createListOf(CellState value, int size) {
        final List<CellState> result = new ArrayList<CellState>(size);
        for (int i = 0; i < size; i++) {
            result.add(value);
        }
        return result;
    }


}