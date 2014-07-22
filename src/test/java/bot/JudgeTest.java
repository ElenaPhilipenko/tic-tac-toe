package bot;

import game.Judge;
import model.BoardState;
import model.CellState;
import model.GameState;
import model.Stroke;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Elena Kurilina
 */
public class JudgeTest {
    private final Judge judge = new Judge();
    private final StrokeMaker strokeMaker = new StrokeMaker();

    @Test
    public void testDetectNotEndedGameWith2DirtyCells() {
        final BoardState boardState = createBoardState();

        strokeMaker.makeStroke(boardState, new Stroke(1, 1, CellState.PLAYER1));
        strokeMaker.makeStroke(boardState, new Stroke(1, 0, CellState.PLAYER2));

        Assert.assertEquals(judge.detectGameState(boardState), GameState.NOT_ENDED);
    }

    @Test
    public void testDetectNotEndedGameWithEmptyBoard() {
        final BoardState boardState = createBoardState();

        Assert.assertEquals(judge.detectGameState(boardState), GameState.NOT_ENDED);
    }

    @Test
    public void testDetectWonByHorizontalLine() {
        final BoardState boardState = createBoardState();

        strokeMaker.makeStroke(boardState, new Stroke(1, 0, CellState.PLAYER1));
        strokeMaker.makeStroke(boardState, new Stroke(1, 1, CellState.PLAYER1));
        strokeMaker.makeStroke(boardState, new Stroke(1, 2, CellState.PLAYER1));

        Assert.assertEquals(judge.detectGameState(boardState), GameState.PLAYER1_WON);
    }

    @Test
    public void testDetectWonByVerticalLine() {
        final BoardState boardState = createBoardState();

        strokeMaker.makeStroke(boardState, new Stroke(0, 0, CellState.PLAYER1));
        strokeMaker.makeStroke(boardState, new Stroke(1, 0, CellState.PLAYER1));
        strokeMaker.makeStroke(boardState, new Stroke(2, 0, CellState.PLAYER1));

        Assert.assertEquals(judge.detectGameState(boardState), GameState.PLAYER1_WON);
    }

    @Test
    public void testDetectWonByMainDiagonal() {
        final BoardState boardState = createBoardState();

        strokeMaker.makeStroke(boardState, new Stroke(0, 0, CellState.PLAYER1));
        strokeMaker.makeStroke(boardState, new Stroke(1, 1, CellState.PLAYER1));
        strokeMaker.makeStroke(boardState, new Stroke(2, 2, CellState.PLAYER1));
        strokeMaker.makeStroke(boardState, new Stroke(1, 2, CellState.PLAYER2));

        Assert.assertEquals(judge.detectGameState(boardState), GameState.PLAYER1_WON);
    }

    @Test
    public void testDetectWonByMinorDiagonal() {
        final BoardState boardState = createBoardState();

        strokeMaker.makeStroke(boardState, new Stroke(2, 0, CellState.PLAYER1));
        strokeMaker.makeStroke(boardState, new Stroke(1, 1, CellState.PLAYER1));
        strokeMaker.makeStroke(boardState, new Stroke(0, 2, CellState.PLAYER1));
        strokeMaker.makeStroke(boardState, new Stroke(0, 1, CellState.PLAYER2));

        Assert.assertEquals(judge.detectGameState(boardState), GameState.PLAYER1_WON);
    }

    @Test
    public void testDetectTie() {
        final BoardState boardState = createBoardState();

        strokeMaker.makeStroke(boardState, new Stroke(0, 1, CellState.PLAYER2));
        strokeMaker.makeStroke(boardState, new Stroke(0, 0, CellState.PLAYER1));
        strokeMaker.makeStroke(boardState, new Stroke(0, 2, CellState.PLAYER1));

        strokeMaker.makeStroke(boardState, new Stroke(1, 0, CellState.PLAYER2));
        strokeMaker.makeStroke(boardState, new Stroke(1, 1, CellState.PLAYER1));
        strokeMaker.makeStroke(boardState, new Stroke(1, 2, CellState.PLAYER2));

        strokeMaker.makeStroke(boardState, new Stroke(2, 0, CellState.PLAYER2));
        strokeMaker.makeStroke(boardState, new Stroke(2, 1, CellState.PLAYER1));
        strokeMaker.makeStroke(boardState, new Stroke(2, 2, CellState.PLAYER2));

        Assert.assertEquals(judge.detectGameState(boardState), GameState.TIE);
    }

    private BoardState createBoardState() {
        final int columns = 3;
        return new BoardState(columns);
    }


}
