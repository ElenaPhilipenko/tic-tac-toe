package game.player;

import game.Player;
import model.BoardState;
import model.CellState;
import model.Stroke;

import java.io.BufferedReader;
import java.io.IOException;
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
    public Stroke findMove(BoardState board) {
        printBoard(board);
        String[] stroke = new String[0];
        try {
            stroke = reader.readLine().split(" ");
        } catch (IOException e) {
            System.out.println("Can not parse stroke.");
        }
        int column = Integer.parseInt(stroke[0]);
        int row = Integer.parseInt(stroke[1]);
        return new Stroke(column, row, state);
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
