package spreadsheet;

import java.math.BigDecimal;

public class Operand extends Symbol {

    private final BigDecimal value;

    public static Operand parse(String literal) {
        return new Operand(literal);
    }

    public Operand(BigDecimal value) {
        this.value = value;
    }

    private Operand(String literal) {
        this.value = new BigDecimal(literal);
    }

    public Operand times(Operand multiplier) {
        return new Operand(multiplier.value.multiply(value));
    }

    public Operand plus(Operand addend) {
        return new Operand(addend.value.add(value));
    }

    public Operand minus(Operand subtrahend) {
        return new Operand(value.subtract(subtrahend.value));
    }

    public boolean equals(Object other) {
        return other instanceof Operand && ((Operand) other).value.equals(value);
    }

    public int hashCode() {
        return value.hashCode();
    }

    public String toLiteral() {
        return value.stripTrailingZeros().toString();
    }

    public Operand dividedBy(Operand divisor) {
        return new Operand(value.divide(divisor.value));
    }

    public void accept(SymbolProcessor processor) {
        processor.visitOperand(this);
    }
}
