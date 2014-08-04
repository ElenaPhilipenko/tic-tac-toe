package game.player;

import model.BoardState;
import model.CellState;
import model.Move;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Elena Kurilina
 */
public class MoveTest {

    @Test
    public void testCreateBoardSize() {
        final int columns = 3;
        final BoardState boardState = new BoardState(columns);
        Assert.assertEquals(boardState.getSize(), columns);
    }

    @Test(dependsOnMethods = "testCreateBoardSize")
    public void testCreatedBoardIsEmpty() {
        final int columns = 3;

        final BoardState boardState = new BoardState(columns);

        for (int i = 0; i < boardState.getSize(); i++) {
            for (int j = 0; j > boardState.getSize(); j++) {
                Assert.assertTrue(boardState.getCell(i, j).equals(CellState.EMPTY));
            }

        }
    }

    @Test
    public void testMakingStroke() {
        BoardState board = new BoardState(4);

        final int column = 1;
        final int row = 3;
        board = board.makeMove(new Move(row, column, CellState.PLAYER1));

        Assert.assertTrue(board.getCell(row, column).equals(CellState.PLAYER1));
    }

    @Test
    public void testMakingStrokeDidntChangeOtherCell() {
        BoardState board = new BoardState(3);

        final int column = 1;
        final int row = 2;
        board = board.makeMove(new Move(row, column, CellState.PLAYER1));

        Assert.assertEquals(board.getCell(1, 1), CellState.EMPTY);
    }

    @Test
    public void testMakingStrokeDintOverride() {
        BoardState board = new BoardState(7);
        final int column = 1;
        final int row = 2;

        board = board.makeMove(new Move(row, column, CellState.PLAYER1));
        board = board.makeMove(new Move(row, column, CellState.PLAYER2));

        Assert.assertEquals(board.getCell(row, column), CellState.PLAYER1);
    }
}
