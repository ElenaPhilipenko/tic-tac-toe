package game;

import model.BoardState;
import model.Move;

/**
 * @author Elena Kurilina
 */
public interface Player {

    Move findMove(BoardState board);
}
