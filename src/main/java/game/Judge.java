package game;

import model.BoardState;
import model.CellState;
import model.GameState;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Elena Kurilina
 */
public class Judge {
    private static final Logger LOG = Logger.getLogger(Judge.class);

    public GameState detectGameState(BoardState board) {
        final Set<Set<CellState>> linesToCheck = getLinesToCheck(board);
        return detectState(linesToCheck);
    }

    public void playGame(Player p1, Player p2) {
        final BoardState board = new BoardState(3);
        final StrokeMaker strokeMaker = new StrokeMaker();
        while (detectGameState(board) == GameState.NOT_ENDED) {
            strokeMaker.makeStroke(board, p1.findMove(board));
            if (detectGameState(board) != GameState.NOT_ENDED) {
                break;
            }
            strokeMaker.makeStroke(board, p2.findMove(board));
        }
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
            if (isLineComplete(line)) {
                final CellState winner = line.iterator().next();
                LOG.debug("Winner is: " + winner);
                return winner == CellState.PLAYER1 ? GameState.PLAYER1_WON : GameState.PLAYER2_WON;
            }
            if (line.contains(CellState.EMPTY)) {
                containsEmptyCells = true;
            }
        }
        LOG.debug("Game state is: " + (containsEmptyCells ? GameState.NOT_ENDED : GameState.TIE));
        return containsEmptyCells ? GameState.NOT_ENDED : GameState.TIE;
    }

    private boolean isLineComplete(Set<CellState> line) {
        return line.size() == 1 && line.iterator().next() != CellState.EMPTY;
    }
}
