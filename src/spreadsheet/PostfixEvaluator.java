package spreadsheet;

import java.util.ArrayDeque;
import java.util.Deque;

public class PostfixEvaluator implements SymbolProcessor {

    private final Deque<Symbol> resultStack = new ArrayDeque<Symbol>();

    public static Symbol evaluate(Expression postfix) {
        PostfixEvaluator evaluator = new PostfixEvaluator();
        postfix.traverse(evaluator);
        return evaluator.done();
    }

    public void visitOperator(Operator operator) {
        final Operand secondOperand = (Operand) resultStack.pollFirst();
        final Operand firstOperand = (Operand) resultStack.pollFirst();
        resultStack.addFirst(operator.perform(firstOperand, secondOperand));
    }

    public void visitOperand(Operand operand) {
        resultStack.addFirst(operand);
    }

    private Symbol done() {
        return resultStack.pollFirst();
    }
}