package com.github.elkurilina.game;

import com.github.elkurilina.Player;
import org.apache.log4j.Logger;

/**
 * @author Elena Kurilina
 */
public class Game {
    private static final Logger LOG = Logger.getLogger(Game.class);

    public BoardState playGame(Player p1, Player p2) {
        LOG.info("Game was started.");
        BoardState board = new BoardState(3);
        GameState state = GameState.NOT_ENDED;
        while (board.detectGameState() == GameState.NOT_ENDED) {
            board = waitForMove(p1, board);
            state = board.detectGameState();
            if (state != GameState.NOT_ENDED) {
                break;
            }
            board = waitForMove(p2, board);
        }
        System.out.print("Game result is: " + state);
        return board;
    }

    private BoardState waitForMove(Player player, BoardState board) {
        final Move move = player.findMove(board);
        if (board.getCell(move.cell.row, move.cell.column) != CellState.EMPTY) {
            System.out.println("Choose empty cell for your move.");
            return waitForMove(player, board);
        }
        return board.makeMove(move);
    }


}