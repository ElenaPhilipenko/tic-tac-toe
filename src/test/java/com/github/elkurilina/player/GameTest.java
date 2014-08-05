package com.github.elkurilina.player;

import com.github.elkurilina.game.BoardState;
import com.github.elkurilina.game.CellState;
import com.github.elkurilina.game.GameState;
import com.github.elkurilina.game.Move;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Elena Kurilina
 */
public class GameTest {

    @Test
    public void testDetectNotEndedGameWith2DirtyCells() {
        BoardState boardState = createBoardState();

        boardState = boardState.makeMove(new Move(1, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(0, 1, CellState.PLAYER2));

        Assert.assertEquals(boardState.detectGameState(), GameState.NOT_ENDED);
    }

    @Test
    public void testDetectNotEndedGameWithEmptyBoard() {
        final BoardState boardState = createBoardState();

        Assert.assertEquals(boardState.detectGameState(), GameState.NOT_ENDED);
    }

    @Test
    public void testDetectWonByHorizontalLine() {
        BoardState boardState = createBoardState();

        boardState = boardState.makeMove(new Move(0, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(1, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(2, 1, CellState.PLAYER1));

        Assert.assertEquals(boardState.detectGameState(), GameState.PLAYER1_WON);
    }

    @Test
    public void testDetectWonByVerticalLine() {
        BoardState boardState = createBoardState();

        boardState = boardState.makeMove(new Move(0, 0, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(0, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(0, 2, CellState.PLAYER1));

        Assert.assertEquals(boardState.detectGameState(), GameState.PLAYER1_WON);
    }

    @Test
    public void testDetectWonByMainDiagonal() {
        BoardState boardState = createBoardState();

        boardState = boardState.makeMove(new Move(0, 0, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(1, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(2, 2, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(2, 1, CellState.PLAYER2));

        Assert.assertEquals(boardState.detectGameState(), GameState.PLAYER1_WON);
    }

    @Test
    public void testDetectWonByMinorDiagonal() {
        BoardState boardState = createBoardState();

        boardState = boardState.makeMove(new Move(0, 2, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(1, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(2, 0, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(1, 0, CellState.PLAYER2));

        Assert.assertEquals(boardState.detectGameState(), GameState.PLAYER1_WON);
    }

    @Test
    public void testDetectTie() {
        BoardState boardState = createBoardState();

        boardState = boardState.makeMove(new Move(1, 0, CellState.PLAYER2));
        boardState = boardState.makeMove(new Move(0, 0, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(2, 0, CellState.PLAYER1));

        boardState = boardState.makeMove(new Move(0, 1, CellState.PLAYER2));
        boardState = boardState.makeMove(new Move(1, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(2, 1, CellState.PLAYER2));

        boardState = boardState.makeMove(new Move(0, 2, CellState.PLAYER2));
        boardState = boardState.makeMove(new Move(1, 2, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(2, 2, CellState.PLAYER2));

        Assert.assertEquals(boardState.detectGameState(), GameState.TIE);
    }

    private BoardState createBoardState() {
        final int columns = 3;
        return new BoardState(columns);
    }


}
