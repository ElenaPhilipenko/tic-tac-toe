package game.player;

import game.BoardUtils;
import game.Judge;
import game.Player;
import game.MoveMaker;
import model.BoardState;
import model.CellState;
import model.GameState;
import model.Move;

import java.util.*;

import static model.CellState.getNextPlayer;
import static model.GameState.getWinningStateFor;

/**
 * @author Elena Kurilina
 */
public class SmartPlayer implements Player {

    private static final int VICTORY_COST = 1;
    private static final int TIE_COST = 0;
    private static final int FAIL_COST = -1;

    private final CellState player;
    private final MoveMaker moveMaker = new MoveMaker();
    private final BoardUtils boardUtils = new BoardUtils();
    private final Judge judge = new Judge();
    private final Random random = new Random();

    public SmartPlayer(CellState player) {
        this.player = player;
    }

    @Override
    public Move findMove(BoardState board) {
        final Iterable<Move> availableMoves = boardUtils.findAvailableMoves(board, player);
        final Iterable<EvaluatedMove> evaluatedMoves = evaluateMoves(board, availableMoves);
        return findBestMove(evaluatedMoves);
    }

    private Iterable<EvaluatedMove> evaluateMoves(BoardState board, Iterable<Move> availableMoves) {
        Set<EvaluatedMove> possibleMoves = new HashSet<EvaluatedMove>();
        for (Move move : availableMoves) {
            possibleMoves.add(new EvaluatedMove(move, evaluate(move, board)));
        }
        return possibleMoves;
    }

    private Move findBestMove(Iterable<EvaluatedMove> moves) {
        final List<Move> bestMoves = new ArrayList<Move>();
        int bestCost = FAIL_COST;
        for (EvaluatedMove move : moves) {
            if (move.cost > bestCost) {
                bestCost = move.cost;
                bestMoves.clear();
                bestMoves.add(move.move);
            }
            if (move.cost == bestCost) {
                bestMoves.add(move.move);
            }
        }
        return bestMoves.get(random.nextInt(bestMoves.size()));
    }

    private int evaluate(Move move, BoardState board) {
        final BoardState boardWithMove = new BoardState(board.rows);
        moveMaker.makeStroke(boardWithMove, move);
        GameState gameState = judge.detectGameState(boardWithMove);
        if (gameState == GameState.NOT_ENDED) {
            return findBestCostOfNextMoves(getNextPlayer(move.player), boardWithMove);
        } else {
            return getCostOfGameState(gameState);
        }
    }

    private int findBestCostOfNextMoves(CellState currentPlayer, BoardState board) {
        Iterable<Move> availableMoves = boardUtils.findAvailableMoves(board, currentPlayer);
        int bestCost = findWorstCostFor(currentPlayer);
        for (Move availableMove : availableMoves) {
            final int cost = evaluate(availableMove, board);
            bestCost = chooseMinMax(availableMove.player == player, cost, bestCost);
        }
        return bestCost;
    }

    private int findWorstCostFor(CellState currentPlayer) {
        return chooseMinMax(currentPlayer != player, FAIL_COST, VICTORY_COST);
    }

    private int getCostOfGameState(GameState gameState) {
        final GameState victory = getWinningStateFor(player);
        return victory == gameState ? VICTORY_COST : gameState == GameState.TIE ? TIE_COST : FAIL_COST;
    }

    private int chooseMinMax(boolean max, int option1, int option2) {
        return max ? Math.max(option1, option2) : Math.min(option1, option2);
    }

    private class EvaluatedMove {
        public final Move move;
        public final int cost;

        public EvaluatedMove(Move move, int cost) {
            this.move = move;
            this.cost = cost;
        }
    }

}