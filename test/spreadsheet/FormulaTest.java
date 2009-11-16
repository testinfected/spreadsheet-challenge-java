package spreadsheet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class FormulaTest {

    @Test public void
    supportConstantFormula() {
        assertThat(resultOf("7"), equalTo("7"));
    }

    @Test public void
    handlesMultiplicationCorrectly() {
        assertThat(resultOf("3*2*2"), equalTo("12"));
    }

    @Test public void
    handlesAdditionCorrectly() {
        assertThat(resultOf("3+2+2"), equalTo("7"));
    }

    @Test public void
    handlesSubtractionsCorrectly() {
        assertThat(resultOf("6-2-2"), equalTo("2"));
    }

    @Test public void
    handlesDivisionCorrectly() {
        assertThat(resultOf("30/2/5"), equalTo("3"));
    }

    @Test public void
    supportsMixedOperations() {
        assertThat(resultOf("3*2+2"), equalTo("8"));
    }

    @Test public void
    handlesDecimalsProperly() {
        assertThat(resultOf("3.5*3"), equalTo("10.5"));
    }

    @Test public void
    supportsParens() {
        assertThat(resultOf("(7+3)"), equalTo("10"));
    }

    private String resultOf(String formula) {
        return new Formula(formula).evaluate().toLiteral();
    }
}
