package spreadsheet;

public class Plus extends Operator {

    public Plus() {
        super("+", PrecedenceTable.PLUS);
    }

    public Operand perform(Operand first, Operand second) {
        return first.plus(second);
    }
}
