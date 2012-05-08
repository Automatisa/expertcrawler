package org.ipen.miner.model;

import java.util.Set;

public abstract class AbstractClass implements Class {
	protected String name;
	protected String description;
	protected Set<CharSequence> lexicon;

	@Override
	public String name() {
		return name;
	}

	@Override
	public String description() {
		return description;
	}

	@Override
	public Set<CharSequence> lexicon() {
		return lexicon;
	}
}
