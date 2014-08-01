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
        final Iterable<Set<CellState>> linesToCheck = getLinesToCheck(board);
        return detectState(linesToCheck);
    }

    public BoardState playGame(Player p1, Player p2) {
        LOG.info("Game was started.");
        final BoardState board = new BoardState(3);
        final MoveMaker moveMaker = new MoveMaker();
        while (detectGameState(board) == GameState.NOT_ENDED) {
            waitForMove(p1, board, moveMaker);
            final GameState state = detectGameState(board);
            LOG.info(state);
            if (state != GameState.NOT_ENDED) {
                break;
            }
            waitForMove(p2, board, moveMaker);
        }
        return board;
    }

    private void waitForMove(Player player, BoardState board, MoveMaker moveMaker) {
        boolean strokeWasMade = moveMaker.makeStroke(board, player.findMove(board));
        if (!strokeWasMade) {
            LOG.info("Make a valid move.");
            waitForMove(player, board, moveMaker);
        }
    }

    private Iterable<Set<CellState>> getLinesToCheck(BoardState board) {
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

    private boolean isLineComplete(Collection<CellState> line) {
        return line.size() == 1 && line.iterator().next() != CellState.EMPTY;
    }
}
