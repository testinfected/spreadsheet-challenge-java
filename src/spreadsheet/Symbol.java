package spreadsheet;

import java.util.Arrays;
import java.util.Collection;

public abstract class Symbol {

    public static final Collection<? extends Symbol> KNOWN_SYMBOLS = Arrays.asList(
            new Times(),
            new Fraction(),
            new Plus(),
            new Minus(),
            new LeftParen(),
            new RightParen()
    );

    public abstract void accept(SymbolProcessor processor);

    public static Symbol parse(final String literal) {
        for (Symbol symbol : KNOWN_SYMBOLS) {
            if (symbol.literalIs(literal)) return symbol;
        }
        return Operand.parse(literal);
    }

    public static String allSigns() {
        StringBuilder signs = new StringBuilder();
        for (Symbol symbol : KNOWN_SYMBOLS) {
            signs.append(symbol.toLiteral());
        }
        return signs.toString();
    }

    public abstract String toLiteral();

    public boolean literalIs(String symbol) {
        return toLiteral().equals(symbol);
    }

    public boolean hasPrecedenceOver(Symbol other) {
        return precedence() > other.precedence();
    }

    public int precedence() {
        return 0;
    }
}
