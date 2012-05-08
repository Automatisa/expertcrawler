package org.ipen.miner.model2;

public class NegationExpression extends UnaryExpression {
	public NegationExpression(CharSequence operand) {
		super(operand);
		super.operator = "not";
	}

	@Override
	protected boolean evaluation(boolean result) {
		return !result;
	}
}
