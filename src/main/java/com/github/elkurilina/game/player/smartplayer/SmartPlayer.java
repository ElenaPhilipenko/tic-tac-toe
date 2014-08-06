package com.github.elkurilina.game.player.smartplayer;

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
    public Move findMove(Board board) {
        final Collection<Cell> emptyCells = board.findEmptyCells();
        if (emptyCells.size() == board.getSize() * board.getSize()) {
            return new Move(1, 1, player);
        }
        final Iterable<EvaluatedMove> evaluatedMoves = convertToEvaluatedMoves(board, emptyCells);
        return findBestMove(evaluatedMoves);
    }

    private Iterable<EvaluatedMove> convertToEvaluatedMoves(Board board, Iterable<Cell> emptyCells) {
        final Collection<EvaluatedMove> evaluatedMoves = new HashSet<EvaluatedMove>();
        for (Cell cell : emptyCells) {
            final Move move = new Move(cell, player);
            evaluatedMoves.add(new EvaluatedMove(move, evaluate(move, board, 0)));
        }
        return evaluatedMoves;
    }

    private Move findBestMove(Iterable<EvaluatedMove> moves) {
        final List<Move> bestMoves = new ArrayList<Move>();
        MoveCost bestCost = moves.iterator().next().cost;
        for (EvaluatedMove move : moves) {
            if (move.cost.isBetterThan(bestCost)) {
                bestCost = move.cost;
                bestMoves.clear();
                bestMoves.add(move.move);
            }
            if (move.cost.equals(bestCost)) {
                bestMoves.add(move.move);
            }
        }
        return bestMoves.get(random.nextInt(bestMoves.size()));
    }

    private MoveCost evaluate(Move move, Board board, int steps) {
        final Board updatedBoard = board.makeMove(move);
        final GameState gameState = updatedBoard.detectGameState();
        if (gameState == GameState.NOT_ENDED) {
            steps++;
            return findBestCostOfNextMoves(getNextPlayer(move.player), updatedBoard, steps);
        } else {
            return getCostOfGameState(gameState, steps);
        }
    }

    private MoveCost findBestCostOfNextMoves(CellState currentPlayer, Board board, int currentStep) {
        final Iterable<Cell> emptyCells = board.findEmptyCells();
        MoveCost bestCost = new MoveCost(findWorstCostFor(currentPlayer), currentStep);
        for (Cell cell : emptyCells) {
            final MoveCost option = evaluate(new Move(cell, currentPlayer), board, currentStep);
            bestCost = chooseMinMax(currentPlayer == player, option, bestCost);
        }
        return bestCost;
    }

    private int findWorstCostFor(CellState currentPlayer) {
        return currentPlayer != player ? Math.max(FAIL_COST, VICTORY_COST) : Math.min(FAIL_COST, VICTORY_COST);
    }

    private MoveCost getCostOfGameState(GameState gameState, int steps) {
        final GameState victory = getWinningStateFor(player);
        final int cost = victory == gameState ? VICTORY_COST : gameState == GameState.TIE ? TIE_COST : FAIL_COST;
        return new MoveCost(cost, steps);
    }

    private MoveCost chooseMinMax(boolean max, MoveCost option1, MoveCost option2) {
        if (max) {
            return option1.isBetterThan(option2) ? option1 : option2;
        } else {
            return option1.isBetterThan(option2) ? option2 : option1;
        }
    }

    private static class EvaluatedMove {
        public final Move move;
        public final MoveCost cost;

        public EvaluatedMove(Move move, MoveCost cost) {
            this.move = move;
            this.cost = cost;
        }
    }

}