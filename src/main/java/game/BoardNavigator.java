package game;

import model.BoardState;
import model.CellState;
import model.Stroke;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elena Kurilina
 */
public class BoardNavigator {

    public List<Stroke> findAvailableStrokes(BoardState board, CellState player) {
        final List<Stroke> availableStrokes = new ArrayList<Stroke>();
        for (int i = 0; i < board.rows.size(); i++) {
            final List<CellState> row = board.rows.get(i);
            for (int j = 0; j < row.size(); j++) {
                CellState state = row.get(j);
                if (state == CellState.EMPTY) {
                    availableStrokes.add(new Stroke(j, i, player));
                }
            }
        }
        return availableStrokes;
    }
}
