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

    public void processOperator(Operator operator) {
        if (!operatorStack.isEmpty() && !operator.hasPrecedenceOver(operatorStack.peekFirst()))
            outputQueue.add(operatorStack.pollFirst());
        operatorStack.addFirst(operator);
    }

    public void processOperand(Operand operand) {
        outputQueue.add(operand);
    }

    public void processOpeningSymbol(Symbol opener) {
        operatorStack.addFirst(opener);
    }

    public void processClosingSymbol(SymbolMatcher terminator) {
        while (!operatorStack.isEmpty()) {
            Symbol next = operatorStack.pollFirst();
            if (terminator.matches(next)) break;
            outputQueue.add(next);
        }
    }

    public Expression done() {
        for (Symbol operator : operatorStack) {
            outputQueue.add(operator);
        }
        return LeftToRightExpression.from(outputQueue);
    }
}
