package org.ipen.miner.model2;

public abstract class UnaryExpression extends Expression {
	protected CharSequence operand;

	public UnaryExpression(CharSequence operand) {
		this.operand = operand;
	}

	public CharSequence getOperand() {
		return operand;
	}

	@Override
	public boolean evaluate(CharSequence text) {
		if (operand instanceof Expression) {
			return evaluation(((Expression)operand).evaluate(text));
		} else {
			return evaluation(text.toString().contains(operand));
		}
	}

	protected abstract boolean evaluation(boolean result);

	@Override
	public final String toString() {
		return operator.toString().concat("(".concat(operand.toString()).concat(")"));
	}
}
