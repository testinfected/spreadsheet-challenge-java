package spreadsheet;

public abstract class Operator extends Symbol {
    private final String sign;
    private final int precedence;

    protected Operator(String sign, int precedence) {
        this.sign = sign;
        this.precedence = precedence;
    }

    public boolean hasPrecedenceOver(Operator other) {
        return precedence() > other.precedence();
    }

    public int precedence() {
        return precedence;
    }

    public void accept(SymbolProcessor processor) {
        processor.visitOperator(this);
    }

    public String toLiteral() {
        return getSign();
    }

    public boolean symbolIs(String symbol) {
        return sign.equals(symbol);
    }

    public String getSign() {
        return sign;
    }

    public abstract Operand perform(Operand first, Operand second);
}
