package com.github.elkurilina.player;

import com.github.elkurilina.game.*;

import java.util.Collection;

/**
 * @author Elena Kurilina
 */
public class RandomBotPlayer implements Player {
    public final CellState player;

    public RandomBotPlayer(CellState player) {
        this.player = player;
    }

    public Move findMove(Board board) {
        final Collection<Cell> emptyCells = board.findEmptyCells();
        return new Move(emptyCells.iterator().next(), player);
    }


}
