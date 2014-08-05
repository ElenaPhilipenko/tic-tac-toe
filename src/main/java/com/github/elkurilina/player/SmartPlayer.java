package com.github.elkurilina.player;

import com.github.elkurilina.Player;
import com.github.elkurilina.game.*;

import java.util.*;

import static com.github.elkurilina.game.CellState.getNextPlayer;
import static com.github.elkurilina.game.GameState.getWinningStateFor;

/**
 * @author Elena Kurilina
 */
public class SmartPlayer implements Player {

    private static final int VICTORY_COST = 1;
    private static final int TIE_COST = 0;
    private static final int FAIL_COST = -1;

    private final CellState player;
    private final Random random = new Random();

    public SmartPlayer(CellState player) {
        this.player = player;
    }

    @Override
    public Move findMove(BoardState board) {
        final Iterable<Cell> emptyCells = board.findEmptyCells();
        final Iterable<EvaluatedMove> evaluatedMoves = convertToEvaluatedMoves(board, emptyCells);
        return findBestMove(evaluatedMoves);
    }

    private Iterable<EvaluatedMove> convertToEvaluatedMoves(BoardState board, Iterable<Cell> emptyCells) {
        final Set<EvaluatedMove> evaluatedMoves = new HashSet<EvaluatedMove>();
        for (Cell cell : emptyCells) {
            final Move move = new Move(cell, player);
            evaluatedMoves.add(new EvaluatedMove(move, evaluate(move, board)));
        }
        return evaluatedMoves;
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
        final BoardState updatedBoard = board.makeMove(move);
        final GameState gameState = updatedBoard.detectGameState();
        if (gameState == GameState.NOT_ENDED) {
            return findBestCostOfNextMoves(getNextPlayer(move.player), updatedBoard);
        } else {
            return getCostOfGameState(gameState);
        }
    }

    private int findBestCostOfNextMoves(CellState currentPlayer, BoardState board) {
        final Iterable<Cell> emptyCells = board.findEmptyCells();
        int bestCost = findWorstCostFor(currentPlayer);
        for (Cell cell : emptyCells) {
            final int cost = evaluate(new Move(cell, currentPlayer), board);
            bestCost = chooseMinMax(currentPlayer == player, cost, bestCost);
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

    private static class EvaluatedMove {
        public final Move move;
        public final int cost;

        public EvaluatedMove(Move move, int cost) {
            this.move = move;
            this.cost = cost;
        }
    }

}