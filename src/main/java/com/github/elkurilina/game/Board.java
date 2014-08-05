package com.github.elkurilina.game;

import java.util.Collection;

/**
 * @author Elena Kurilina
 */
public interface Board {

    public int getSize();

    public CellState getCell(int row, int column);

    public Board makeMove(Move move);

    public Collection<Cell> findEmptyCells();

    public GameState detectGameState();
}
