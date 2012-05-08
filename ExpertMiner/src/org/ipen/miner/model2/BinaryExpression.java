package org.ipen.miner.model2;

public abstract class BinaryExpression extends Expression {
	protected CharSequence operand1;
	protected CharSequence operand2;
	protected boolean result;

	public BinaryExpression(CharSequence operand1, CharSequence operand2) {
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	public CharSequence getOperand1() {
		return operand1;
	}

	public CharSequence getOperand2() {
		return operand2;
	}

	@Override
	public boolean evaluate(CharSequence text) {
		if (operand1 instanceof Expression) {
			result = evaluation(result, ((Expression)operand1).evaluate(text));
		} else {
			result = evaluation(result, text.toString().contains(operand1));
		}

		if (operand2 instanceof Expression) {
			result = evaluation(result, ((Expression)operand2).evaluate(text));
		} else {
			result = evaluation(result, text.toString().contains(operand2));
		}

		return result;
	}

	protected abstract boolean evaluation(boolean result1, boolean result2);

	@Override
	public final String toString() {
		return "(".concat(operand1.toString().concat(operator.concat(operand2.toString().concat(")"))));
	}
}
