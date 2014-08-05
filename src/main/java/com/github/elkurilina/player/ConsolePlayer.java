package com.github.elkurilina.player;

import com.github.elkurilina.Player;
import com.github.elkurilina.game.BoardState;
import com.github.elkurilina.game.CellState;
import com.github.elkurilina.game.Move;

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
        System.out.println("  012");
        for (int i = 0; i < boardState.getSize(); i++) {
            System.out.print(i + " ");
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
        System.out.println("Enter your move in format: column row");
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
