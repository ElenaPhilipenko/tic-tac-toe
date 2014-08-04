package game.player;

import game.Player;
import model.BoardState;
import model.Cell;
import model.CellState;
import model.Move;

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
