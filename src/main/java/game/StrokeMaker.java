package game;

import model.BoardState;
import model.CellState;
import model.Stroke;


/**
 * @author Elena Kurilina
 */
public class StrokeMaker {

    public void makeStroke(BoardState boardState, Stroke stroke) {
        final CellState state = boardState.rows.get(stroke.row).get(stroke.column);
        if (state == CellState.EMPTY) {
            boardState.rows.get(stroke.row).set(stroke.column, stroke.state);
        }
    }


}
