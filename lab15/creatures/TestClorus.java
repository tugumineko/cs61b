package creatures;

import huglife.*;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class TestClorus {
    @Test
    public void testChoose() {
        Creature c = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = c.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        surrounded.put(Direction.TOP, new Empty());
        surrounded.put(Direction.BOTTOM, new Plip(0.1));
        Action actual2 = c.chooseAction(surrounded);
        Action expected2 = new Action(Action.ActionType.ATTACK,Direction.BOTTOM);
        surrounded.put(Direction.BOTTOM, new Impassible());

        assertEquals(expected2,actual2);

        Action actual3 = c.chooseAction(surrounded);
        Action expected3 = new Action(Action.ActionType.REPLICATE,Direction.TOP);
        c.replicate();
        assertEquals(expected3,actual3);

        Action actual4 = c.chooseAction(surrounded);
        Action expected4 = new Action(Action.ActionType.MOVE,Direction.TOP);

        assertEquals(expected4,actual4);
    }
}
