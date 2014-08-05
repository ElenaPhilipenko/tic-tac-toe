import com.github.elkurilina.game.Game;
import com.github.elkurilina.player.SmartPlayer;
import com.github.elkurilina.player.ConsolePlayer;
import com.github.elkurilina.Player;
import com.github.elkurilina.game.CellState;

import java.io.IOException;

/**
 * @author Elena Kurilina
 */
public class BotVsConsolePlayer {

    public static void main(String[] args) throws IOException {
        final Player smartPlayer = new SmartPlayer(CellState.PLAYER1);
        final Player consolePlayer = new ConsolePlayer(CellState.PLAYER2);
        final Game game = new Game();
        game.playGame(smartPlayer, consolePlayer);
    }


}
