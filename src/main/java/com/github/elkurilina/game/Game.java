package com.github.elkurilina.game;

import com.github.elkurilina.game.board.ListBoard;
import org.apache.log4j.Logger;

/**
 * @author Elena Kurilina
 */
public class Game {
    private static final Logger LOG = Logger.getLogger(Game.class);

    public Board playGame(Player p1, Player p2) {
        LOG.info("Game was started.");
        Board board = new ListBoard(3);
        GameState state = GameState.NOT_ENDED;
        while (board.detectGameState() == GameState.NOT_ENDED) {
            board = waitForMove(p1, board);
            state = board.detectGameState();
            if (state != GameState.NOT_ENDED) {
                break;
            }
            board = waitForMove(p2, board);
        }
        System.out.println("Game result is: " + state);
        return board;
    }

    private Board waitForMove(Player player, Board board) {
        final Move move = player.findMove(board);
        if (board.getCell(move.cell.row, move.cell.column) != CellState.EMPTY) {
            System.out.println("Choose empty cell for your move.");
            return waitForMove(player, board);
        }
        return board.makeMove(move);
    }


}
