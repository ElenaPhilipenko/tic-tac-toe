package com.github.elkurilina.game;

/**
 * @author Elena Kurilina
 */
public enum GameState {
    PLAYER1_WON, PLAYER2_WON, NOT_ENDED, TIE;

    public static GameState getWinningStateFor(CellState player) {
        return player == CellState.PLAYER1 ? GameState.PLAYER1_WON : GameState.PLAYER2_WON;
    }

}
