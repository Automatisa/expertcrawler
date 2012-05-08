package org.ipen.miner.model;

import java.util.List;

public abstract class AbstractClassifier implements Classifier {
	protected List<CharSequence> data;
	protected Model model;

	protected AbstractClassifier(List<CharSequence> data, Model model) {
		this.data = data;
		this.model = model;
	}

	@Override
	public List<CharSequence> data() {
		return data;
	}

	@Override
	public Model model() {
		return model;
	}
}
