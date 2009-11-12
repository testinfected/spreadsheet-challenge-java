package spreadsheet;

public class Fraction extends Operator {

    public Fraction() {
        super("/", PrecedenceTable.FRACTION);
    }

    public Operand perform(Operand numerator, Operand divisor) {
        return numerator.dividedBy(divisor);
    }
}
