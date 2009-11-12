package spreadsheet;

import java.util.Arrays;
import java.util.Collection;

public abstract class Symbol {

    public static final Collection<? extends Operator> OPERATIONS =
        Arrays.asList(new Times(), new Fraction(), new Plus(), new Minus());

    public abstract void accept(SymbolProcessor processor);

    public static Symbol parse(final String symbol) {
        for (Operator operator : OPERATIONS) {
            if (operator.symbolIs(symbol)) return operator;
        }
        return Operand.parse(symbol);
    }

    public static String operatorSigns() {
        StringBuilder signs = new StringBuilder();
        for (Operator operator : OPERATIONS) {
            signs.append(operator.getSign());
        }
        return signs.toString();
    }

    public abstract String toLiteral();
}
