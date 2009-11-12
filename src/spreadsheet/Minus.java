package spreadsheet;

public class Minus extends Operator {

    public Minus() {
        super("-", PrecedenceTable.MINUS);
    }

    public Operand perform(Operand first, Operand second) {
        return first.minus(second);
    }
}
