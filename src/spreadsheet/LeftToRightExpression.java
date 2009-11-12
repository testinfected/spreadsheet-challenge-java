package spreadsheet;

import java.util.*;

public class LeftToRightExpression implements Expression {

    public final List<Symbol> symbols = new ArrayList<Symbol>();

    public static LeftToRightExpression infix(String infixExpression) {
        StringTokenizer tokenizer = new StringTokenizer(infixExpression, Symbol.operatorSigns(), true);
        LeftToRightExpression expression = LeftToRightExpression.empty();
        while (tokenizer.hasMoreTokens()) {
            expression.add(Symbol.parse(tokenizer.nextToken()));
        }
        return expression;
    }

    public static LeftToRightExpression from(Collection<Symbol> symbols) {
        return new LeftToRightExpression(symbols);
    }

    public static LeftToRightExpression empty() {
        return new LeftToRightExpression(Collections.<Symbol>emptyList());
    }

    private LeftToRightExpression(Collection<Symbol> symbols) {
        this.symbols.addAll(symbols);
    }

    public void add(Symbol symbol) {
        symbols.add(symbol);
    }

    public void traverse(SymbolProcessor processor) {
        for (Symbol symbol : symbols) {
            symbol.accept(processor);
        }
    }
}
