package spreadsheet;

public class Formula {

    private final String infix;

    public Formula(String infixExpression) {
        this.infix = infixExpression;
    }

    public Symbol evaluate() {
        return evaluate(toPostfix(infix));
    }

    private Expression toPostfix(String infix) {
        return ShuntingYard.convertToPostfix(LeftToRightExpression.infix(infix));
    }

    private Symbol evaluate(Expression postfix) {
        return PostfixEvaluator.evaluate(postfix);
    }
}