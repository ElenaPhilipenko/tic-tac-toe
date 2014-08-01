package game.player;

import game.BoardUtils;
import game.Player;
import model.BoardState;
import model.CellState;
import model.Move;

import java.util.Collection;

/**
 * @author Elena Kurilina
 */
public class RandomBotPlayer implements Player {
    public final CellState state;
    private final BoardUtils boardUtils = new BoardUtils();

    public RandomBotPlayer(CellState state) {
        this.state = state;
    }

    public Move findMove(BoardState board) {
        final Collection<Move> availableMoves = boardUtils.findAvailableMoves(board, state);
        return boardUtils.chooseRandomMove(availableMoves);
    }


}
