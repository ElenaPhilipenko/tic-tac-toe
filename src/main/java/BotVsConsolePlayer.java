import game.Game;
import game.player.SmartPlayer;
import game.player.ConsolePlayer;
import game.Player;
import model.CellState;

import java.io.IOException;

/**
 * @author Elena Kurilina
 */
public class BotVsConsolePlayer {

    public static void main(String[] args) throws IOException {
        final Player stan = new SmartPlayer(CellState.PLAYER1);
        final Player lena = new ConsolePlayer(CellState.PLAYER2);
        final Game game = new Game();
        System.out.print("");
        game.playGame(stan, lena);
    }


}
