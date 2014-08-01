package game.player;

import game.BoardUtils;
import game.MoveMaker;
import model.BoardState;
import model.CellState;
import model.Move;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Elena Kurilina
 */
public class BoardNavigatorTest {

    @Test
    public void testGetStrokesFromEmptyBoard() {
        final BoardState boardState = new BoardState(3);
        final BoardUtils navigator = new BoardUtils();

        Assert.assertEquals(navigator.findAvailableMoves(boardState, CellState.PLAYER1).size(), 9);
    }

    @Test
    public void testGetStrokesFromNotEmptyBoard() {
        final BoardState boardState = new BoardState(3);
        final BoardUtils navigator = new BoardUtils();
        final MoveMaker moveMaker = new MoveMaker();

        moveMaker.makeStroke(boardState, new Move(1, 1, CellState.PLAYER2));


        Assert.assertEquals(navigator.findAvailableMoves(boardState, CellState.PLAYER1).size(), 8);
    }

    @Test
    public void testGetStrokesFromFilledBoard() {
        final BoardState boardState = new BoardState(3);
        final BoardUtils navigator = new BoardUtils();
        final MoveMaker moveMaker = new MoveMaker();

        moveMaker.makeStroke(boardState, new Move(0, 0, CellState.PLAYER2));
        moveMaker.makeStroke(boardState, new Move(0, 1, CellState.PLAYER1));
        moveMaker.makeStroke(boardState, new Move(0, 2, CellState.PLAYER1));
        moveMaker.makeStroke(boardState, new Move(1, 0, CellState.PLAYER1));
        moveMaker.makeStroke(boardState, new Move(1, 1, CellState.PLAYER1));
        moveMaker.makeStroke(boardState, new Move(1, 2, CellState.PLAYER2));
        moveMaker.makeStroke(boardState, new Move(2, 0, CellState.PLAYER2));
        moveMaker.makeStroke(boardState, new Move(2, 1, CellState.PLAYER2));
        moveMaker.makeStroke(boardState, new Move(2, 2, CellState.PLAYER2));

        Assert.assertEquals(navigator.findAvailableMoves(boardState, CellState.PLAYER1).size(), 0);
    }


}
