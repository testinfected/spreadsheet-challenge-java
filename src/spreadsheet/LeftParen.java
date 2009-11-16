package spreadsheet;

public class LeftParen extends Symbol {

    @Override
    public void accept(SymbolProcessor processor) {
        processor.processOpeningSymbol(this);
    }

    @Override
    public String toLiteral() {
        return "(";
    }
}
