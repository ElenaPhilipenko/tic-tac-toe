package game;

import model.BoardState;
import model.Stroke;

/**
 * @author Elena Kurilina
 */
public interface Player {

    Stroke findMove(BoardState board);
}
