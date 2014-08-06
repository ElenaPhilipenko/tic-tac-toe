package com.github.elkurilina.game.player;

import com.github.elkurilina.game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Elena Kurilina
 */
public class RandomBotPlayer implements Player {
    public final CellState player;
    private final Random random = new Random();

    public RandomBotPlayer(CellState player) {
        this.player = player;
    }

    public Move findMove(Board board) {
        final List<Cell> emptyCells = new ArrayList<>( board.findEmptyCells());
        return new Move(emptyCells.get(random.nextInt(emptyCells.size())), player);
    }


}
