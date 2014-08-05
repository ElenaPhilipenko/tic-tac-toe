package com.github.elkurilina.player;

import com.github.elkurilina.board.ListBoard;
import com.github.elkurilina.game.Board;
import com.github.elkurilina.game.CellState;
import com.github.elkurilina.game.GameState;
import com.github.elkurilina.game.Move;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Elena Kurilina
 */
public class GameTest {
    private Board boardState;

    @BeforeMethod
    public void createBoard() {
        boardState = new ListBoard(3);
    }

    @Test
    public void testDetectNotEndedGameWith2DirtyCells() {

        boardState = boardState.makeMove(new Move(1, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(0, 1, CellState.PLAYER2));

        Assert.assertEquals(boardState.detectGameState(), GameState.NOT_ENDED);
    }

    @Test
    public void testDetectNotEndedGameWithEmptyBoard() {

        Assert.assertEquals(boardState.detectGameState(), GameState.NOT_ENDED);
    }

    @Test
    public void testDetectWonByHorizontalLine() {

        boardState = boardState.makeMove(new Move(0, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(1, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(2, 1, CellState.PLAYER1));

        Assert.assertEquals(boardState.detectGameState(), GameState.PLAYER1_WON);
    }

    @Test
    public void testDetectWonByVerticalLine() {

        boardState = boardState.makeMove(new Move(0, 0, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(0, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(0, 2, CellState.PLAYER1));

        Assert.assertEquals(boardState.detectGameState(), GameState.PLAYER1_WON);
    }

    @Test
    public void testDetectWonByMainDiagonal() {

        boardState = boardState.makeMove(new Move(0, 0, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(1, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(2, 2, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(2, 1, CellState.PLAYER2));

        Assert.assertEquals(boardState.detectGameState(), GameState.PLAYER1_WON);
    }

    @Test
    public void testDetectWonByMinorDiagonal() {

        boardState = boardState.makeMove(new Move(0, 2, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(1, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(2, 0, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(1, 0, CellState.PLAYER2));

        Assert.assertEquals(boardState.detectGameState(), GameState.PLAYER1_WON);
    }

    @Test
    public void testDetectTie() {

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

}
