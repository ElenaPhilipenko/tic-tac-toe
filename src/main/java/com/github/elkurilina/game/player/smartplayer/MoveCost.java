package com.github.elkurilina.game.player.smartplayer;

/**
 * @author Elena Kurilina
 */
public class MoveCost {
    private final int cost;
    private final int steps;

    public MoveCost(int cost, int steps) {
        this.cost = cost;
        this.steps = steps;
    }

    public boolean isBetterThan(MoveCost moveCost) {
        if (cost == moveCost.cost) {
            return steps < moveCost.steps;
        } else {
            return cost > moveCost.cost;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MoveCost that = (MoveCost) o;

        if (cost != that.cost) return false;
        if (steps != that.steps) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cost;
        result = 31 * result + steps;
        return result;
    }
}
