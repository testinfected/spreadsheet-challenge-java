package spreadsheet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ShuntingYard implements SymbolProcessor {

    private final Deque<Symbol> operatorStack = new ArrayDeque<Symbol>();
    private final List<Symbol> outputQueue = new ArrayList<Symbol>();

    public static Expression convertToPostfix(Expression infix) {
        ShuntingYard shuntingYard = new ShuntingYard();
        infix.traverse(shuntingYard);
        return shuntingYard.done();
    }

    public void visitOperator(Operator operator) {
        if (!operatorStack.isEmpty() && !operator.hasPrecedenceOver((Operator) operatorStack.peekFirst()))
            outputQueue.add(operatorStack.pollFirst());
        operatorStack.addFirst(operator);
    }

    public void visitOperand(Operand operand) {
        outputQueue.add(operand);
    }

    public Expression done() {
        for (Symbol operator : operatorStack) {
            outputQueue.add(operator);
        }
        return LeftToRightExpression.from(outputQueue);
    }
}
