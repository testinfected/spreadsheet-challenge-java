package spreadsheet;

import java.util.HashMap;
import java.util.Map;

public class SpreadSheet {
    private static final String EMPTY_CONTENT = "";
    private static final String INTEGER_PATTERN = "\\s*\\d+(\\.\\d+)?\\s*";

    private final Map<String, String> cells = new HashMap<String, String>();

    public void put(String cellReference, String value) {
        cells.put(cellReference, value);
    }

    public String get(String cellReference) {
        String literal = getLiteral(cellReference);
        if (isNumeric(literal)) return parseNumeric(literal);
        if (isFormula(literal)) return computeFormula(literal);
        return literal;
    }

    private String parseNumeric(String literal) {
        return literal.trim();
    }

    private String computeFormula(String literal) {
        String expression = removeEqualSign(literal);
        return compute(expression).toLiteral();
    }

    private Symbol compute(String expression) {
        return new Formula(expression).evaluate();
    }

    private String removeEqualSign(String literal) {
        return literal.substring(1);
    }

    private boolean isFormula(String literal) {
        return literal.startsWith("=");
    }

    public String getLiteral(String cellReference) {
        return accommodateEmptyCell(getContentOf(cellReference));
    }

    private String getContentOf(String cellReference) {
        return cells.get(cellReference);
    }

    private String accommodateEmptyCell(String value) {
        return value != null ? value : EMPTY_CONTENT;
    }

    private boolean isNumeric(String literal) {
        return literal.matches(INTEGER_PATTERN);
    }
}
