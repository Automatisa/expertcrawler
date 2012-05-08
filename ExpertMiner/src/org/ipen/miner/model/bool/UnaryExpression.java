package org.ipen.miner.model.bool;

import java.util.List;
import org.ipen.miner.model.Classifier;

public abstract class UnaryExpression extends Expression {
	protected Expression operand;

	public UnaryExpression(Expression operand) {
		this.operand = operand;
	}

	public Expression getOperand() {
		return operand;
	}

	@Override
	public Classifier classifier(List<CharSequence> data) {
		return new BooleanClassifier(data, this);
	}

	@Override
	public float classify(List<CharSequence> data) {
		if (evaluate(operand.classifier(data).classify() == 1)) {
			return 1;
		} else {
			return 0;
		}
	}

	protected abstract boolean evaluate(boolean result);

	@Override
	public final String toString() {
		return operator.toString().concat("(".concat(operand.toString()).concat(")"));
	}
}
