package bot;

import model.BoardState;
import model.CellState;
import model.Stroke;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author Elena Kurilina
 */
public class StrokeTest {
    StrokeMaker strokeMaker;

    @BeforeMethod
    public void setUp() throws Exception {
        strokeMaker = new StrokeMaker();
    }

    @Test
    public void testCreateBoardSize() {
        final int columns = 3;
        final BoardState boardState = new BoardState(columns);
        Assert.assertEquals(boardState.rows.size(), columns);
        Assert.assertEquals(boardState.rows.get(0).size(), columns);
    }

    @Test(dependsOnMethods = "testCreateBoardSize")
    public void testCreatedBoardIsEmpty() {
        final int columns = 3;

        final BoardState boardState = new BoardState(columns);

        for (List<CellState> list : boardState.rows) {
            for (CellState value : list) {
                Assert.assertTrue(value.equals(CellState.EMPTY));
            }

        }
    }

    @Test
    public void testMakingStroke() {
        final BoardState board = new BoardState(4);

        final int column = 1;
        final int row = 3;
        strokeMaker.makeStroke(board, new Stroke(column, row, CellState.PLAYER1));

        Assert.assertTrue(board.rows.get(row).get(column).equals(CellState.PLAYER1));
    }

    @Test
    public void testMakingStrokeDidntChangeOtherCell() {
        final BoardState board = new BoardState(3);

        final int column = 1;
        final int row = 2;
        strokeMaker.makeStroke(board, new Stroke(column, row, CellState.PLAYER1));

        Assert.assertEquals(board.rows.get(1).get(1), CellState.EMPTY);
    }

    @Test
    public void testMakingStrokeDintOverride() {
        final BoardState board = new BoardState(7);
        final int column = 1;
        final int row = 2;

        strokeMaker.makeStroke(board, new Stroke(column, row, CellState.PLAYER1));
        strokeMaker.makeStroke(board, new Stroke(column, row, CellState.PLAYER2));

        Assert.assertEquals(board.rows.get(row).get(column), CellState.PLAYER1);
    }
}
