import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator offBy4 = new OffByN(4);

    @Test
    public void testOffByN(){
        assertTrue(offBy4.equalChars('a','e'));
        assertTrue(offBy4.equalChars('e','a'));
        assertFalse(offBy4.equalChars('a','a'));
        assertFalse(offBy4.equalChars('*','*'));
    }

}
