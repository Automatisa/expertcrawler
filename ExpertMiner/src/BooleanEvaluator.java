package org.ipen.miner.ir.model;

import org.ipen.miner.model2.BinaryExpression;
import org.ipen.miner.model2.Expression;
import org.ipen.miner.model2.UnaryExpression;

public class BooleanEvaluator {
	private CharSequence text;
	private boolean result = false;

	public BooleanEvaluator(CharSequence text) {
		this.text = text;
	}

	public boolean evaluate(Expression expression) {
		if (expression instanceof UnaryExpression) {
			UnaryExpression unaryExpression = (UnaryExpression)expression;

			if (unaryExpression.operand instanceof Expression) {
				result = result || evaluate((Expression)unaryExpression.operand);
			} else {
				result = result || text.toString().contains(unaryExpression.operand);
			}
		} else if (expression instanceof BinaryExpression) {
			BinaryExpression binaryExpression = (BinaryExpression)expression;

			if (binaryExpression.operand1 instanceof Expression) {
				result = result || evaluate((Expression)binaryExpression.operand1);
			} else {
				result = result || text.toString().contains(binaryExpression.operand1);
			}

			if (binaryExpression.operand2 instanceof Expression) {
				result = result || evaluate((Expression)binaryExpression.operand2);
			} else {
				result = result || text.toString().contains(binaryExpression.operand2);
			}
		}

		return result;
	}
}

/*
	public boolean evaluate(Expression expression) {
		if (expression.operand1 instanceof Expression) {
			result = result || evaluate((Expression)expression.operand1);
		} else {
			result = result || text.toString().contains(expression.operand1);
		}

		if (expression.operand2 instanceof Expression) {
			result = result || evaluate((Expression)expression.operand2);
		} else {
			result = result || text.toString().contains(expression.operand2);
		}

		return result;
	}
*/