package game.player;

import game.Player;
import model.BoardState;
import model.CellState;
import model.Move;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * @author Elena Kurilina
 */
public class ConsolePlayer implements Player {

    public final CellState state;
    private final BufferedReader reader;

    public ConsolePlayer(CellState state) {
        this.state = state;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public Move findMove(BoardState board) {
        printBoard(board);
        try {
            String[] stroke = reader.readLine().split(" ");
            int column = Integer.parseInt(stroke[0]);
            int row = Integer.parseInt(stroke[1]);
            return new Move(column, row, state);
        } catch (Exception e) {
            System.out.println("Can not parse stroke.");
            return findMove(board);
        }
    }

    private static void printBoard(BoardState boardState) {
        for (List<CellState> row : boardState.rows) {
            for (CellState state : row) {
                System.out.print(state == CellState.PLAYER1 ? "x" : state == CellState.PLAYER2 ? "o" : "-");
            }
            System.out.print("\n");
        }
    }
}
