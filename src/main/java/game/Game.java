package game;

import model.BoardState;
import model.GameState;
import model.Move;
import org.apache.log4j.Logger;

/**
 * @author Elena Kurilina
 */
public class Game {
    private static final Logger LOG = Logger.getLogger(Game.class);

    public BoardState playGame(Player p1, Player p2) {
        LOG.info("Game was started.");
        BoardState board = new BoardState(3);
        while (board.detectGameState() == GameState.NOT_ENDED) {
            board = waitForMove(p1, board);
            final GameState state = board.detectGameState();
            LOG.info(state);
            if (state != GameState.NOT_ENDED) {
                break;
            }
            board = waitForMove(p2, board);
        }
        return board;
    }

    private BoardState waitForMove(Player player, BoardState board) {
        final Move move = player.findMove(board);
        final BoardState boardState = board.makeMove(move);
        if (boardState.getCell(move.cell.row, move.cell.column) != move.player) {
            LOG.info("Make a valid move.");
            return waitForMove(player, board);
        }
        return boardState;
    }


}
