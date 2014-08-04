import game.Game;
import game.player.RandomBotPlayer;
import model.CellState;

/**
 * @author Elena Kurilina
 */
public class BotVsBot {

    public static void main(String[] args) {
        final RandomBotPlayer stan = new RandomBotPlayer(CellState.PLAYER1);
        final RandomBotPlayer lena = new RandomBotPlayer(CellState.PLAYER2);
        final Game game = new Game();
        game.playGame(stan, lena);

    }

}
