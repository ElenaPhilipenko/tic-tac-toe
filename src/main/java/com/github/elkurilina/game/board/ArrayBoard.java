package com.github.elkurilina.game.board;

import com.github.elkurilina.game.*;

import java.util.*;

/**
 * @author Elena Kurilina
 */
public class ArrayBoard implements Board {

    private final CellState[][] board;

    public ArrayBoard(int size) {
        this.board = new CellState[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = CellState.EMPTY;
            }
        }
    }

    private ArrayBoard(CellState[][] rows) {
        this.board = new CellState[rows.length][rows.length];
        for (int i = 0; i < getSize(); i++) {
            board[i] = Arrays.copyOf(rows[i], getSize());
        }
    }

    public int getSize() {
        return board.length;
    }

    public CellState getCell(int row, int column) {
        return board[row][column];
    }

    public ArrayBoard makeMove(Move move) {
        final ArrayBoard stateClone = new ArrayBoard(board);
        final CellState state = getCell(move.cell.row, move.cell.column);
        if (state == CellState.EMPTY) {
            stateClone.setCell(move.cell, move.player);
        }
        return stateClone;
    }

    public Collection<Cell> findEmptyCells() {
        final Set<Cell> emptyCells = new HashSet<Cell>();
        for (int row = 0; row < getSize(); row++) {
            for (int column = 0; column < getSize(); column++) {
                if (getCell(row, column) == CellState.EMPTY) {
                    emptyCells.add(new Cell(row, column));
                }
            }
        }
        return emptyCells;
    }

    public GameState detectGameState() {
        final Iterable<Set<CellState>> linesToCheck = getLinesToCheck();
        return detectState(linesToCheck);
    }

    private GameState detectState(Iterable<Set<CellState>> lines) {
        boolean containsEmptyCells = false;
        for (Collection<CellState> line : lines) {
            if (isLineComplete(line)) {
                return GameState.getWinningStateFor(line.iterator().next());
            }
            if (line.contains(CellState.EMPTY)) {
                containsEmptyCells = true;
            }
        }
        return containsEmptyCells ? GameState.NOT_ENDED : GameState.TIE;
    }

    private Iterable<Set<CellState>> getLinesToCheck() {
        final Set<Set<CellState>> lines = new HashSet<Set<CellState>>();
        final Set<CellState> mainDiagonal = new HashSet<CellState>();
        final Set<CellState> minorDiagonal = new HashSet<CellState>();
        for (int i = 0; i < getSize(); i++) {
            final CellState[] row = board[i];
            mainDiagonal.add(row[i]);
            minorDiagonal.add(row[(row.length - i - 1)]);
            lines.add(getColumn(i));
            final Set<CellState> rowSet = new HashSet<CellState>();
            Collections.addAll(rowSet, row);
            lines.add(rowSet);
        }
        lines.add(mainDiagonal);
        lines.add(minorDiagonal);
        return lines;
    }

    private boolean isLineComplete(Collection<CellState> line) {
        return line.size() == 1 && line.iterator().next() != CellState.EMPTY;
    }

    private Set<CellState> getColumn(int i) {
        final Set<CellState> column = new HashSet<CellState>();
        for (int j = 0; j < getSize(); j++) {
            column.add(board[j][i]);
        }
        return column;
    }

    private void setCell(Cell cell, CellState value) {
        board[cell.row][cell.column] = value;
    }


}