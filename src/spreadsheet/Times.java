package spreadsheet;

public class Times extends Operator {

    public Times() {
        super("*", PrecedenceTable.TIMES);
    }

    public Operand perform(Operand first, Operand second) {
        return first.times(second);
    }
}
