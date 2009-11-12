package spreadsheet;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;

public class FormulaTest {

    @Test public void
    supportConstantFormula() {
        assertThat("7", evaluatesTo("7"));
    }

    @Test public void
    handlesMultiplicationCorrectly() {
        assertThat("3*2*2", evaluatesTo("12"));
    }

    @Test public void
    handlesAdditionCorrectly() {
        assertThat("3+2+2", evaluatesTo("7"));
    }

    @Test public void
    handlesSubtractionsCorrectly() {
        assertThat("6-2-2", evaluatesTo("2"));
    }

    @Test public void
    handlesDivisionCorrectly() {
        assertThat("30/2/5", evaluatesTo("3"));
    }

    @Test public void
    supportsMixedOperations() {
        assertThat("3*2+2", evaluatesTo("8"));
    }

    @Test public void
    handlesDecimalsProperly() {
        assertThat("3.5*3", evaluatesTo("10.5"));
    }
    
    private Matcher<String> evaluatesTo(final String expected) {
        return new ExpressionEvaluatesToMatcher(expected);
    }

    private static class ExpressionEvaluatesToMatcher extends TypeSafeMatcher<String> {

        private final String expected;

        public ExpressionEvaluatesToMatcher(String expected) {
            this.expected = expected;
        }

        public boolean matchesSafely(String expression) {
            return expected.equals(evalute(expression));
        }

        public void describeTo(Description description) {
            description.appendValue(expected);
        }

        private String evalute(String formula) {
            return new Formula(formula).evaluate().toLiteral();
        }
    }
}
