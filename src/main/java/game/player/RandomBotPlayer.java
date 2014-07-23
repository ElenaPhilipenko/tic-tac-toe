package game.player;

import game.BoardNavigator;
import game.Player;
import model.BoardState;
import model.CellState;
import model.Stroke;

import java.util.List;
import java.util.Random;

/**
 * @author Elena Kurilina
 */
public class RandomBotPlayer implements Player {
    private final Random random = new Random();
    private final BoardNavigator boardNavigator = new BoardNavigator();

    public RandomBotPlayer(CellState state) {
        this.state = state;
    }

    public final CellState state;

    public Stroke findMove(BoardState board) {
        final List<Stroke> availableStrokes = boardNavigator.findAvailableStrokes(board, state);
        return chooseRandomStroke(availableStrokes);

    }

    private Stroke chooseRandomStroke(List<Stroke> availableStrokes) {
        return availableStrokes.get(random.nextInt(availableStrokes.size()));
    }



}
