package com.github.elkurilina;

import com.github.elkurilina.game.BoardState;
import com.github.elkurilina.game.Move;

/**
 * @author Elena Kurilina
 */
public interface Player {

    Move findMove(BoardState board);
}
