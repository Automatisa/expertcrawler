package org.ipen.miner.nlp;

public class PunctuationRemover implements Remover {
	public PunctuationRemover() {
	}

	@Override
	public boolean isRemovable(CharSequence word) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsRemovables(CharSequence text) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

	@Override
	public String remove(CharSequence text) throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
}
