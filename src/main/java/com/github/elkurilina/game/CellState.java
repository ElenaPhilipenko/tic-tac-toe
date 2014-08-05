package com.github.elkurilina.game;

/**
 * @author Elena Kurilina
 */
public enum CellState {

    EMPTY, PLAYER1, PLAYER2;

    public static CellState getNextPlayer(CellState state) {
        return state == PLAYER1 ? PLAYER2 : PLAYER1;
    }
}
