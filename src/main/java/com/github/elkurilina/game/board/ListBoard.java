package com.github.elkurilina.game.board;

import com.github.elkurilina.game.*;

import java.util.*;

/**
 * @author Elena Kurilina
 */
public class ListBoard implements Board {

    private final List<List<CellState>> rows;

    public ListBoard(int size) {
        this.rows = new ArrayList<List<CellState>>(size);
        for (int i = 0; i < size; i++) {
            this.rows.add(createListOf(CellState.EMPTY, size));
        }
    }

    private ListBoard(List<List<CellState>> rows) {
        final List<List<CellState>> clone = new ArrayList<List<CellState>>();
        for (List<CellState> row : rows) {
            clone.add(new ArrayList<CellState>(row));
        }
        this.rows = clone;
    }

    public int getSize() {
        return rows.size();
    }

    public CellState getCell(int row, int column) {
        return rows.get(row).get(column);
    }

    public ListBoard makeMove(Move move) {
        final ListBoard stateClone = new ListBoard(rows);
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
            final List<CellState> row = rows.get(i);
            mainDiagonal.add(row.get(i));
            minorDiagonal.add(row.get(row.size() - i - 1));
            lines.add(getColumn(i));
            lines.add(new HashSet<CellState>(row));
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
        for (List<CellState> row : rows) {
            column.add(row.get(i));
        }
        return column;
    }

    private void setCell(Cell cell, CellState value) {
        rows.get(cell.row).set(cell.column, value);
    }

    private List<CellState> createListOf(CellState value, int size) {
        final List<CellState> result = new ArrayList<CellState>(size);
        for (int i = 0; i < size; i++) {
            result.add(value);
        }
        return result;
    }

}