package org.ipen.miner.model2;

public class ExclusiveDisjunctionExpression extends DisjunctionExpression {
	public ExclusiveDisjunctionExpression(CharSequence operand1, CharSequence operand2) {
		super(operand1, operand2);
		super.operator = " xor ";
	}

	@Override
	protected boolean evaluation(boolean result1, boolean result2) {
		return result1 ^ result2;
	}
}
