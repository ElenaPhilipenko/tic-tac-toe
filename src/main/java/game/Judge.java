package game;

import model.BoardState;
import model.CellState;
import model.GameState;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * @author Elena Kurilina
 */
public class Judge {
    private static final Logger LOG = Logger.getLogger(Judge.class);

    public GameState detectGameState(BoardState board) {
        final Set<Set<CellState>> linesToCheck = getLinesToCheck(board);
        return detectState(linesToCheck);
    }

    private Set<Set<CellState>> getLinesToCheck(BoardState board) {
        final Set<Set<CellState>> lines = new HashSet<Set<CellState>>();
        final Set<CellState> mainDiagonal = new HashSet<CellState>();
        final Set<CellState> minorDiagonal = new HashSet<CellState>();
        for (int i = 0; i < board.getSize(); i++) {
            final List<CellState> row = board.rows.get(i);
            mainDiagonal.add(row.get(i));
            minorDiagonal.add(row.get(row.size() - i - 1));
            lines.add(getColumn(board, i));
            lines.add(new HashSet<CellState>(row));
        }
        lines.add(mainDiagonal);
        lines.add(minorDiagonal);
        return lines;
    }

    private Set<CellState> getColumn(BoardState board, int i) {
        final Set<CellState> column = new HashSet<CellState>();
        for (List<CellState> row : board.rows) {
            column.add(row.get(i));
        }
        return column;
    }

    private GameState detectState(Collection<Set<CellState>> lines) {
        boolean containsEmptyCells = false;
        for (Set<CellState> line : lines) {
            if (line.size() == 1) {
                final CellState winner = line.iterator().next();
                if (winner != CellState.EMPTY) {
                    LOG.debug("Winner is: " + winner);
                    return winner == CellState.PLAYER1 ? GameState.PLAYER1_WON : GameState.PLAYER2_WON;
                }
            }
            if (line.contains(CellState.EMPTY)) {
                containsEmptyCells = true;
            }
        }
        LOG.debug("Game state is: " + (containsEmptyCells ? GameState.NOT_ENDED : GameState.TIE));
        return containsEmptyCells ? GameState.NOT_ENDED : GameState.TIE;
    }
}
