package spreadsheet;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import org.junit.Test;

import java.math.BigDecimal;

public class OperandTest {

    @Test public void
    canBeComparedToAnotherOperand() {
        Operand operand = Operand.parse("7.5");
        Operand equivalent = Operand.parse("7.5");
        assertThat(operand, equalTo(equivalent));

        Operand different = Operand.parse("9");
        assertThat(operand, not(equalTo(different)));

        BigDecimal notAnOperand = new BigDecimal("7.5");
        assertThat(operand.equals(notAnOperand), is(false));
    }

    @Test public void
    calculatesItsHashCode() {
        Operand operand = Operand.parse("7.5");
        Operand equivalent = Operand.parse("7.5");
        assertThat(operand.hashCode(), equalTo(equivalent.hashCode()));

        Operand different = Operand.parse("9");
        assertThat(operand.hashCode(), not(equalTo(different.hashCode())));
    }

    @Test public void
    displaysAsIntegerWhenDecimalPartIsZero() {
        assertThat(Operand.parse("2.0").toLiteral(), equalTo("2"));
        assertThat(Operand.parse("2.").toLiteral(), equalTo("2"));
    }
}
