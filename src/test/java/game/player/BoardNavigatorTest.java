package game.player;

import game.BoardNavigator;
import game.StrokeMaker;
import model.BoardState;
import model.CellState;
import model.Stroke;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Elena Kurilina
 */
public class BoardNavigatorTest {

    @Test
    public void testGetStrokesFromEmptyBoard() {
        final BoardState boardState = new BoardState(3);
        final BoardNavigator navigator = new BoardNavigator();

        Assert.assertEquals(navigator.findAvailableStrokes(boardState, CellState.PLAYER1).size(), 9);
    }

    @Test
    public void testGetStrokesFromNotEmptyBoard() {
        final BoardState boardState = new BoardState(3);
        final BoardNavigator navigator = new BoardNavigator();
        final StrokeMaker strokeMaker = new StrokeMaker();

        strokeMaker.makeStroke(boardState, new Stroke(1, 1, CellState.PLAYER2));


        Assert.assertEquals(navigator.findAvailableStrokes(boardState, CellState.PLAYER1).size(), 8);
    }

    @Test
    public void testGetStrokesFromFilledBoard() {
        final BoardState boardState = new BoardState(3);
        final BoardNavigator navigator = new BoardNavigator();
        final StrokeMaker strokeMaker = new StrokeMaker();

        strokeMaker.makeStroke(boardState, new Stroke(0, 0, CellState.PLAYER2));
        strokeMaker.makeStroke(boardState, new Stroke(0, 1, CellState.PLAYER2));
        strokeMaker.makeStroke(boardState, new Stroke(0, 2, CellState.PLAYER2));
        strokeMaker.makeStroke(boardState, new Stroke(1, 0, CellState.PLAYER2));
        strokeMaker.makeStroke(boardState, new Stroke(1, 1, CellState.PLAYER2));
        strokeMaker.makeStroke(boardState, new Stroke(1, 2, CellState.PLAYER2));
        strokeMaker.makeStroke(boardState, new Stroke(2, 0, CellState.PLAYER2));
        strokeMaker.makeStroke(boardState, new Stroke(2, 1, CellState.PLAYER2));
        strokeMaker.makeStroke(boardState, new Stroke(2, 2, CellState.PLAYER2));


        Assert.assertEquals(navigator.findAvailableStrokes(boardState, CellState.PLAYER1).size(), 0);
    }


}
