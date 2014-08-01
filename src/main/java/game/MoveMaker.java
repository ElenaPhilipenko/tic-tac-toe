package game;

import model.BoardState;
import model.CellState;
import model.Move;


/**
 * @author Elena Kurilina
 */
public class MoveMaker {

    public boolean makeStroke(BoardState boardState, Move move) {
        final CellState state = boardState.rows.get(move.row).get(move.column);
        if (state == CellState.EMPTY) {
            boardState.rows.get(move.row).set(move.column, move.player);
            return true;
        }
        return false;
    }


}
