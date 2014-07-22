import bot.Bot;
import bot.StrokeMaker;
import game.Judge;
import model.BoardState;
import model.CellState;
import model.GameState;

import java.util.List;

/**
 * @author Elena Kurilina
 */
public class Game {

    public static void main(String[] args) {
        final Bot stan = new Bot(CellState.PLAYER1);
        final Bot lena = new Bot(CellState.PLAYER2);
        final BoardState board = new BoardState(3);
        final StrokeMaker strokeMaker = new StrokeMaker();
        final Judge judge = new Judge();
        while (judge.detectGameState(board) == GameState.NOT_ENDED) {
            strokeMaker.makeStroke(board, stan.findMove(board));
            if (judge.detectGameState(board) != GameState.NOT_ENDED) {
                break;
            }
            strokeMaker.makeStroke(board, lena.findMove(board));
        }
        printBoard(board);

    }

    private static void printBoard(BoardState boardState) {
        for (List<CellState> row : boardState.rows) {
            for (CellState state : row) {
                System.out.print(state + " ");
            }
            System.out.println("\n");
        }
    }
}
