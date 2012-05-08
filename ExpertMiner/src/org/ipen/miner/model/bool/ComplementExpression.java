package org.ipen.miner.model.bool;

public class ComplementExpression extends UnaryExpression {
	public ComplementExpression(Expression operand) {
		super(operand);
		operator = "not";
	}

	@Override
	protected boolean evaluate(boolean result) {
		return !result;
	}
}
