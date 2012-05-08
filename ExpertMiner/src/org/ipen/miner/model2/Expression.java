package org.ipen.miner.model2;

public abstract class Expression implements CharSequence {
	protected String operator;

	public abstract boolean evaluate(CharSequence text);

	@Override
	public final char charAt(int index) {
		return toString().charAt(index);
	}

	@Override
	public final int length() {
		return toString().length();
	}

	@Override
	public final CharSequence subSequence(int start, int end) {
		return toString().subSequence(start, end);
	}
}
