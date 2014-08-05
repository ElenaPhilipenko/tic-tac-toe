package com.github.elkurilina.player;

import com.github.elkurilina.board.ListBoard;
import com.github.elkurilina.game.CellState;
import com.github.elkurilina.game.Move;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Elena Kurilina
 */
public class MoveTest {

    @Test
    public void testCreateBoardSize() {
        final int columns = 3;
        final ListBoard boardState = new ListBoard(columns);
        Assert.assertEquals(boardState.getSize(), columns);
    }

    @Test(dependsOnMethods = "testCreateBoardSize")
    public void testCreatedBoardIsEmpty() {
        final int columns = 3;

        final ListBoard boardState = new ListBoard(columns);

        for (int i = 0; i < boardState.getSize(); i++) {
            for (int j = 0; j > boardState.getSize(); j++) {
                Assert.assertTrue(boardState.getCell(i, j).equals(CellState.EMPTY));
            }

        }
    }

    @Test
    public void testMakingMove() {
        ListBoard board = new ListBoard(4);

        final int column = 1;
        final int row = 3;
        board = board.makeMove(new Move(row, column, CellState.PLAYER1));

        Assert.assertTrue(board.getCell(row, column).equals(CellState.PLAYER1));
    }

    @Test
    public void testMakingStrokeDidntChangeOtherCell() {
        ListBoard board = new ListBoard(3);

        final int column = 1;
        final int row = 2;
        board = board.makeMove(new Move(row, column, CellState.PLAYER1));

        Assert.assertEquals(board.getCell(1, 1), CellState.EMPTY);
    }

    @Test
    public void testMakingStrokeDintOverride() {
        ListBoard board = new ListBoard(7);
        final int column = 1;
        final int row = 2;

        board = board.makeMove(new Move(row, column, CellState.PLAYER1));
        board = board.makeMove(new Move(row, column, CellState.PLAYER2));

        Assert.assertEquals(board.getCell(row, column), CellState.PLAYER1);
    }
}
