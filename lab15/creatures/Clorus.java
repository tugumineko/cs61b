package creatures;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.Map;
import java.util.List;
import java.util.Map;

import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.HugLifeUtils;
import huglife.Occupant;

public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    /**
     * return the color red = 34, green = 0, blue = 231.
     */
    public Color color() {
        r = 34;
        b = 231;
        g = 0;
        return color(r, g, b);
    }

    /**
     * If a Clorus attacks another creature, it should gain that creature’s energy.
     * This should happen in the attack() method, not in chooseAction().
     * You do not need to worry about making sure the attacked creature dies—the simulator does that for you.
     */
    public void attack(Creature c) {
        energy = energy + c.energy();
    }

    public void move() {
        energy = energy - 0.03;
    }

    public void stay(){
        energy = energy - 0.01;
    }

    /** Cloruses and their offspring each get 50% of the energy, with none
     *  lost to the process. Now that's efficiency! Returns a baby
     *  Clorus.
     */
    public Clorus replicate() {
        energy = energy*0.5;
        return this;
    }

    /** If there are no empty squares, the Clorus will STAY (even if there are Plips nearby they could attack).
     *  Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     *  Otherwise, if the Clorus has energy greater than or equal to one, it will REPLICATE to a random empty square.
     *  Otherwise, the Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plip = getNeighborsOfType(neighbors, "plip");

        if (empties.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else if (!plip.isEmpty()) {
            Direction moveDir = HugLifeUtils.randomEntry(plip);
            return new Action(Action.ActionType.ATTACK, moveDir);
        } else if (energy >= 1) {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, moveDir);
        } else {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.MOVE, moveDir);
        }
    }
}
