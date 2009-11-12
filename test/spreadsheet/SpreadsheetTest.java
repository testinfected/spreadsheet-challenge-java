package spreadsheet;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class SpreadsheetTest {
    private SpreadSheet sheet = new SpreadSheet();

    @Test public void
    cellsAreEmptyByDefault() {
        assertThat(valueOf("A1"), equalTo(""));
        assertThat(valueOf("X99"), equalTo(""));
        assertThat(valueOf("X99"), equalTo(""));
        assertThat(contentOf("ZB13"), equalTo(""));
    }

    @Test public void
    multipleCellsAreStoredAndCanBeRetrieved() {
        sheet.put("A1", "first cell");
        sheet.put("B59", "second cell");

        assertThat(valueOf("A1"), equalTo("first cell"));
        assertThat(valueOf("B59"), equalTo("second cell"));
    }

    @Test public void
    numericalValuesAreRecognizedAndDisplayedAsNumbers() {
        assertThat(valueOf(aCellContaining(" 1234 ")), equalTo("1234"));
    }

    @Test public void
    decimalsAreRecognizedAsNumbers() {
        assertThat(valueOf(aCellContaining(" 7.5 ")), equalTo("7.5"));
    }

    @Test public void
    stringsAreNotConfusedWithNumerics() {
        assertThat(valueOf(aCellContaining(" 1234X ")), equalTo(" 1234X "));
        assertThat(valueOf(aCellContaining("ABC")), equalTo("ABC"));
        assertThat(valueOf(aCellContaining(" ")), equalTo(" "));
    }

    @Test public void
    cellLiteralValuesArePreservedForEditing() {
        assertThat(contentOf(aCellContaining(" 1234 ")), equalTo(" 1234 "));
    }

    @Test public void
    handlesComplexOperations() {
        assertThat(valueOf(aCellContaining("=3*2+4-4+2")), equalTo("8"));
    }

    private String valueOf(final String cellReference) {
        return sheet.get(cellReference);
    }

    private String contentOf(final String cellReference) {
        return sheet.getLiteral(cellReference);
    }

    private String aCellContaining(final String literal) {
        sheet.put("A1", literal);
        return "A1";
    }
}
