package game.player;

import game.Game;
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

    private SmartPlayer smartPlayer;
    private BoardState board;

    @BeforeMethod
    public void setUp() throws Exception {
        smartPlayer = new SmartPlayer(CellState.PLAYER2);
        board = new BoardState(3);
    }

    @Test
    public void testFindBestMoveFrom6Possible() {

        board = board.makeMove(new Move(1, 1, CellState.PLAYER1));//x-0    b - best move
        board = board.makeMove(new Move(0, 2, CellState.PLAYER2));//-x-
        board = board.makeMove(new Move(0, 0, CellState.PLAYER1));//--b
        final Move best = smartPlayer.findMove(board);

        Assert.assertEquals(best, new Move(2, 2, CellState.PLAYER2));

    }

    @Test
    public void testFindBestMoveFromTrickyState() {

        board = board.makeMove(new Move(1, 1, CellState.PLAYER1));//x-b
        board = board.makeMove(new Move(2, 2, CellState.PLAYER2));//-x-
        board = board.makeMove(new Move(0, 0, CellState.PLAYER1));//b-0
        final Move best = smartPlayer.findMove(board);

        Assert.assertTrue(best.equals(new Move(0, 2, CellState.PLAYER2))
                || best.equals(new Move(2, 0, CellState.PLAYER2)));
    }

    @Test
    public void testFindWinningMoveByVerticalLine() {
        board = board.makeMove(new Move(1, 1, CellState.PLAYER1));//-x0
        board = board.makeMove(new Move(0, 2, CellState.PLAYER2));//xx0
        board = board.makeMove(new Move(1, 0, CellState.PLAYER1));//--b
        board = board.makeMove(new Move(1, 2, CellState.PLAYER2));
        board = board.makeMove(new Move(0, 1, CellState.PLAYER1));
        final Move best = smartPlayer.findMove(board);

        Assert.assertEquals(best, new Move(2, 2, CellState.PLAYER2));
    }

    @Test
    public void testFindWinningMoveByHorizontalLine() {
        board = board.makeMove(new Move(1, 1, CellState.PLAYER1));//--x
        board = board.makeMove(new Move(2, 0, CellState.PLAYER2));//xx-
        board = board.makeMove(new Move(0, 2, CellState.PLAYER1));//00b
        board = board.makeMove(new Move(2, 1, CellState.PLAYER2));
        board = board.makeMove(new Move(1, 0, CellState.PLAYER1));
        final Move best = smartPlayer.findMove(board);

        Assert.assertEquals(best, new Move(2, 2, CellState.PLAYER2));
    }

    @Test
    public void testFindWinningMoveByMinorDiagonal() {
        board = board.makeMove(new Move(1, 1, CellState.PLAYER1));//-0x
        board = board.makeMove(new Move(0, 1, CellState.PLAYER2));//-x0
        board = board.makeMove(new Move(0, 2, CellState.PLAYER1));//b--
        board = board.makeMove(new Move(1, 2, CellState.PLAYER2));
        final Move best = smartPlayer.findMove(board);

        Assert.assertEquals(best, new Move(2, 0, CellState.PLAYER2));
    }

    @Test
    public void testFindWinningMoveByMainDiagonal() {
        board = board.makeMove(new Move(1, 1, CellState.PLAYER1));//x0-
        board = board.makeMove(new Move(0, 1, CellState.PLAYER2));//-x0
        board = board.makeMove(new Move(0, 0, CellState.PLAYER1));//--b
        board = board.makeMove(new Move(1, 2, CellState.PLAYER2));
        final Move best = smartPlayer.findMove(board);

        Assert.assertEquals(best, new Move(2, 2, CellState.PLAYER2));
    }


    @Test
    public void testFindBestMoveFrom4Possible() {

        board = board.makeMove(new Move(1, 1, CellState.PLAYER1));//x-0
        board = board.makeMove(new Move(0, 2, CellState.PLAYER2));//bxx
        board = board.makeMove(new Move(0, 0, CellState.PLAYER1));//--0
        board = board.makeMove(new Move(2, 2, CellState.PLAYER2));
        board = board.makeMove(new Move(1, 2, CellState.PLAYER1));
        final Move best = smartPlayer.findMove(board);

        Assert.assertEquals(best, new Move(1, 0, CellState.PLAYER2));
    }

    @Test
    public void testFindBestMoveFrom2Possible() {

        board = board.makeMove(new Move(1, 1, CellState.PLAYER1));//xb0
        board = board.makeMove(new Move(0, 2, CellState.PLAYER2));//0xx
        board = board.makeMove(new Move(0, 0, CellState.PLAYER1));//-x0
        board = board.makeMove(new Move(2, 2, CellState.PLAYER2));
        board = board.makeMove(new Move(1, 2, CellState.PLAYER1));
        board = board.makeMove(new Move(1, 0, CellState.PLAYER2));
        board = board.makeMove(new Move(2, 1, CellState.PLAYER1));
        final Move best = smartPlayer.findMove(board);

        Assert.assertEquals(best, new Move(0, 1, CellState.PLAYER2));

    }

    @Test
    public void testSmartPlayerNotBeatByRandomPlayer() {
        final Game game = new Game();

        final BoardState boardState = game.playGame(new RandomBotPlayer(CellState.PLAYER2), new SmartPlayer(CellState.PLAYER1));
        final GameState winner = boardState.detectGameState();

        Assert.assertTrue(winner == GameState.PLAYER1_WON || winner == GameState.TIE);
    }

    @Test
    public void testSmartVsSmart() {
        final Game game = new Game();

        final BoardState boardState = game.playGame(new SmartPlayer(CellState.PLAYER2), new SmartPlayer(CellState.PLAYER1));
        final GameState winner = boardState.detectGameState();

        Assert.assertTrue(winner == GameState.TIE);
    }


}
