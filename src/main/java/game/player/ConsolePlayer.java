package game.player;

import game.Player;
import model.BoardState;
import model.CellState;
import model.Move;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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

    private static void printBoard(BoardState boardState) {
        for (int i = 0; i < boardState.getSize(); i++) {
            for (int j = 0; j < boardState.getSize(); j++) {
                final CellState cell = boardState.getCell(i, j);
                System.out.print(cell == CellState.PLAYER1 ? "x" : cell == CellState.PLAYER2 ? "o" : "-");
            }
            System.out.print("\n");
        }
    }

    @Override
    public Move findMove(BoardState board) {
        printBoard(board);
        try {
            String[] move = reader.readLine().split(" ");
            int column = Integer.parseInt(move[0]);
            int row = Integer.parseInt(move[1]);
            return new Move(row, column, state);
        } catch (Exception e) {
            System.out.println("Can not parse move.");
            return findMove(board);
        }
    }
}
