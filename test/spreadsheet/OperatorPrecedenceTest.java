package spreadsheet;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OperatorPrecedenceTest {

    private Operator plus = new Plus();
    private Operator minus = new Minus();
    private Operator times = new Times();
    private Operator fraction = new Fraction();

    @Test public void
    timesHasPrecedenceOverPlus() {
        assertTrue(times.hasPrecedenceOver(plus));
    }

    @Test public void
    timesHasPrecedenceOverMinus() {
        assertTrue(times.hasPrecedenceOver(minus));
    }

    @Test public void
    fractionHasSamePrecedenceAsTimes() {
        assertFalse(fraction.hasPrecedenceOver(times));
        assertFalse(times.hasPrecedenceOver(fraction));
    }

    @Test public void
    plusHasSamePrecedenceHasMinus() {
        assertFalse(plus.hasPrecedenceOver(minus));
        assertFalse(minus.hasPrecedenceOver(plus));
    }
}
