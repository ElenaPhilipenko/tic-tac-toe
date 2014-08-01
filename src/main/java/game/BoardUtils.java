package game;

import model.BoardState;
import model.CellState;
import model.Move;

import java.util.*;

/**
 * @author Elena Kurilina
 */
public class BoardUtils {
    private final Random random = new Random();

    public Collection<Move> findAvailableMoves(BoardState board, CellState player) {
        final List<Move> availableMoves = new ArrayList<Move>();
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (getState(board, i, j) == CellState.EMPTY) {
                    availableMoves.add(new Move(j, i, player));
                }
            }
        }
        return availableMoves;
    }

    private CellState getState(BoardState board, int i, int j) {
        return board.rows.get(i).get(j);
    }

    public Move chooseRandomMove(Collection<Move> availableMoves) {
        final List<Move> moves = new ArrayList<Move>(availableMoves);
        return moves.get(random.nextInt(availableMoves.size()));
    }

}
