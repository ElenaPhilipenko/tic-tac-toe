package com.github.elkurilina.game.player;

import com.github.elkurilina.game.board.ArrayBoard;
import com.github.elkurilina.game.Board;
import com.github.elkurilina.game.CellState;
import com.github.elkurilina.game.Move;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * @author Elena Kurilina
 */
public class BoardTest {


    private Board boardState;
    @BeforeMethod
    public void createBoard(){
        boardState = new ArrayBoard(3);
    }

    @Test
    public void testGetStrokesFromEmptyBoard() {
        Assert.assertEquals(boardState.findEmptyCells().size(), 9);
    }

    @Test
    public void testGetStrokesFromNotEmptyBoard() {
        boardState = boardState.makeMove(new Move(1, 1, CellState.PLAYER2));

        Assert.assertEquals(boardState.findEmptyCells().size(), 8);
    }

    @Test
    public void testGetStrokesFromFilledBoard() {

        boardState = boardState.makeMove(new Move(0, 0, CellState.PLAYER2));
        boardState = boardState.makeMove(new Move(1, 0, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(2, 0, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(0, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(1, 1, CellState.PLAYER1));
        boardState = boardState.makeMove(new Move(2, 1, CellState.PLAYER2));
        boardState = boardState.makeMove(new Move(0, 2, CellState.PLAYER2));
        boardState = boardState.makeMove(new Move(1, 2, CellState.PLAYER2));
        boardState = boardState.makeMove(new Move(2, 2, CellState.PLAYER2));

        Assert.assertEquals(boardState.findEmptyCells().size(), 0);
    }


}
