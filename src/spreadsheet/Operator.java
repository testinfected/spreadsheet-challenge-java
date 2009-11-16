package spreadsheet;

public abstract class Operator extends Symbol {
    private final String sign;
    private final int precedence;

    protected Operator(String sign, int precedence) {
        this.sign = sign;
        this.precedence = precedence;
    }

    public void accept(SymbolProcessor processor) {
        processor.processOperator(this);
    }

    public String toLiteral() {
        return sign;
    }

    @Override
    public int precedence() {
        return precedence;
    }

    public abstract Operand perform(Operand first, Operand second);
}
