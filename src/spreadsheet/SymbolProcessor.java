package spreadsheet;

public interface SymbolProcessor {

    void processOperator(Operator operator);

    void processOperand(Operand operand);

    void processOpeningSymbol(Symbol opening);

    void processClosingSymbol(SymbolMatcher terminator);
}

