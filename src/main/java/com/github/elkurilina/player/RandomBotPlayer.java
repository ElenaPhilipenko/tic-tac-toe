package com.github.elkurilina.player;

import com.github.elkurilina.Player;
import com.github.elkurilina.game.BoardState;
import com.github.elkurilina.game.Cell;
import com.github.elkurilina.game.CellState;
import com.github.elkurilina.game.Move;

import java.util.Collection;

/**
 * @author Elena Kurilina
 */
public class RandomBotPlayer implements Player {
    public final CellState player;

    public RandomBotPlayer(CellState player) {
        this.player = player;
    }

    public Move findMove(BoardState board) {
        final Collection<Cell> emptyCells = board.findEmptyCells();
        return new Move(emptyCells.iterator().next(), player);
    }


}
