package spreadsheet;

public interface SymbolProcessor {

    void visitOperator(Operator operator);

    void visitOperand(Operand operand);
}
