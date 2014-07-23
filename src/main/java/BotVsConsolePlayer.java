import game.Judge;
import game.player.RandomBotPlayer;
import game.player.ConsolePlayer;
import game.Player;
import model.CellState;

import java.io.IOException;

/**
 * @author Elena Kurilina
 */
public class BotVsConsolePlayer {

    public static void main(String[] args) throws IOException {
        final Player stan = new RandomBotPlayer(CellState.PLAYER1);
        final Player lena = new ConsolePlayer(CellState.PLAYER2);
        final Judge judge = new Judge();
        System.out.print("");
        judge.playGame(stan, lena);
    }


}
