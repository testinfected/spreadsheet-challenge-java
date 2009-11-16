package spreadsheet;

public class RightParen extends Symbol implements SymbolMatcher {

    @Override
    public void accept(SymbolProcessor processor) {
        processor.processClosingSymbol(this);
    }

    @Override
    public String toLiteral() {
        return ")";
    }

    public boolean matches(Symbol other) {
        return other instanceof LeftParen;
    }
}