package game.player;

import game.Judge;
import game.MoveMaker;
import model.BoardState;
import model.CellState;
import model.GameState;
import model.Move;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Elena Kurilina
 */
public class SmartPlayerTest {

    private MoveMaker moveMaker;
    private SmartPlayer smartPlayer;
    private BoardState board;

    @BeforeMethod
    public void setUp() throws Exception {
        moveMaker = new MoveMaker();
        smartPlayer = new SmartPlayer(CellState.PLAYER2);
        board = new BoardState(3);
    }

    @Test
    public void testFindBestMoveFrom6Possible() {

        moveMaker.makeStroke(board, new Move(1, 1, CellState.PLAYER1));//x-0    b - best move
        moveMaker.makeStroke(board, new Move(2, 0, CellState.PLAYER2));//-x-
        moveMaker.makeStroke(board, new Move(0, 0, CellState.PLAYER1));//--b
        final Move best = smartPlayer.findMove(board);

        Assert.assertEquals(best, new Move(2, 2, CellState.PLAYER2));

    }

    @Test
    public void testFindBestMoveFromTrickyState() {

        moveMaker.makeStroke(board, new Move(1, 1, CellState.PLAYER1));//x-b
        moveMaker.makeStroke(board, new Move(2, 2, CellState.PLAYER2));//-x-
        moveMaker.makeStroke(board, new Move(0, 0, CellState.PLAYER1));//b-0
        final Move best = smartPlayer.findMove(board);

        Assert.assertTrue(best.equals(new Move(2, 0, CellState.PLAYER2))
                || best.equals(new Move(0, 2, CellState.PLAYER2)));
    }

    @Test
    public void testFindWinningMoveByVerticalLine() {
        moveMaker.makeStroke(board, new Move(1, 1, CellState.PLAYER1));//-x0
        moveMaker.makeStroke(board, new Move(2, 0, CellState.PLAYER2));//xx0
        moveMaker.makeStroke(board, new Move(0, 1, CellState.PLAYER1));//--b
        moveMaker.makeStroke(board, new Move(2, 1, CellState.PLAYER2));
        moveMaker.makeStroke(board, new Move(1, 0, CellState.PLAYER1));
        final Move best = smartPlayer.findMove(board);

        Assert.assertEquals(best, new Move(2, 2, CellState.PLAYER2));
    }

    @Test
    public void testFindWinningMoveByHorizontalLine() {
        moveMaker.makeStroke(board, new Move(1, 1, CellState.PLAYER1));//--x
        moveMaker.makeStroke(board, new Move(0, 2, CellState.PLAYER2));//xx-
        moveMaker.makeStroke(board, new Move(2, 0, CellState.PLAYER1));//00b
        moveMaker.makeStroke(board, new Move(1, 2, CellState.PLAYER2));
        moveMaker.makeStroke(board, new Move(0, 1, CellState.PLAYER1));
        final Move best = smartPlayer.findMove(board);

        Assert.assertEquals(best, new Move(2, 2, CellState.PLAYER2));
    }

    @Test
    public void testFindWinningMoveByMinorDiagonal() {
        moveMaker.makeStroke(board, new Move(1, 1, CellState.PLAYER1));//-0x
        moveMaker.makeStroke(board, new Move(1, 0, CellState.PLAYER2));//-x0
        moveMaker.makeStroke(board, new Move(2, 0, CellState.PLAYER1));//b--
        moveMaker.makeStroke(board, new Move(2, 1, CellState.PLAYER2));
        final Move best = smartPlayer.findMove(board);

        Assert.assertEquals(best, new Move(0, 2, CellState.PLAYER2));
    }

    @Test
    public void testFindWinningMoveByMainDiagonal() {
        moveMaker.makeStroke(board, new Move(1, 1, CellState.PLAYER1));//x0-
        moveMaker.makeStroke(board, new Move(1, 0, CellState.PLAYER2));//-x0
        moveMaker.makeStroke(board, new Move(0, 0, CellState.PLAYER1));//--b
        moveMaker.makeStroke(board, new Move(2, 1, CellState.PLAYER2));
        final Move best = smartPlayer.findMove(board);

        Assert.assertEquals(best, new Move(2, 2, CellState.PLAYER2));
    }


    @Test
    public void testFindBestMoveFrom4Possible() {

        moveMaker.makeStroke(board, new Move(1, 1, CellState.PLAYER1));//x-0
        moveMaker.makeStroke(board, new Move(2, 0, CellState.PLAYER2));//bxx
        moveMaker.makeStroke(board, new Move(0, 0, CellState.PLAYER1));//--0
        moveMaker.makeStroke(board, new Move(2, 2, CellState.PLAYER2));
        moveMaker.makeStroke(board, new Move(2, 1, CellState.PLAYER1));
        final Move best = smartPlayer.findMove(board);

        Assert.assertEquals(best, new Move(0, 1, CellState.PLAYER2));
    }

    @Test
    public void testFindBestMoveFrom2Possible() {

        moveMaker.makeStroke(board, new Move(1, 1, CellState.PLAYER1));//xb0
        moveMaker.makeStroke(board, new Move(2, 0, CellState.PLAYER2));//0xx
        moveMaker.makeStroke(board, new Move(0, 0, CellState.PLAYER1));//-x0
        moveMaker.makeStroke(board, new Move(2, 2, CellState.PLAYER2));
        moveMaker.makeStroke(board, new Move(2, 1, CellState.PLAYER1));
        moveMaker.makeStroke(board, new Move(0, 1, CellState.PLAYER2));
        moveMaker.makeStroke(board, new Move(1, 2, CellState.PLAYER1));
        final Move best = smartPlayer.findMove(board);

        Assert.assertEquals(best, new Move(1, 0, CellState.PLAYER2));

    }

    @Test
    public void testSmartPlayerNotBeatByRandomPlayer() {
        final Judge judge = new Judge();

        final BoardState boardState = judge.playGame(new RandomBotPlayer(CellState.PLAYER2), new SmartPlayer(CellState.PLAYER1));
        final GameState winner = judge.detectGameState(boardState);

        Assert.assertTrue(winner == GameState.PLAYER1_WON || winner == GameState.TIE);
    }

    @Test
    public void testSmartVsSmart() {
        final Judge judge = new Judge();

        final BoardState boardState = judge.playGame(new SmartPlayer(CellState.PLAYER2), new SmartPlayer(CellState.PLAYER1));
        final GameState winner = judge.detectGameState(boardState);

        Assert.assertTrue(winner == GameState.TIE);
    }


}
