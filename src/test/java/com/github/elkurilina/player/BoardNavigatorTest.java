package com.github.elkurilina.player;

import com.github.elkurilina.game.BoardState;
import com.github.elkurilina.game.CellState;
import com.github.elkurilina.game.Move;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Elena Kurilina
 */
public class BoardNavigatorTest {

    @Test
    public void testGetStrokesFromEmptyBoard() {
        final BoardState boardState = new BoardState(3);

        Assert.assertEquals(boardState.findEmptyCells().size(), 9);
    }

    @Test
    public void testGetStrokesFromNotEmptyBoard() {
        BoardState boardState = new BoardState(3);

        boardState = boardState.makeMove(new Move(1, 1, CellState.PLAYER2));


        Assert.assertEquals(boardState.findEmptyCells().size(), 8);
    }

    @Test
    public void testGetStrokesFromFilledBoard() {
        BoardState boardState = new BoardState(3);

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
