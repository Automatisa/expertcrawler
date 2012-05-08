package org.ipen.miner.model.bool;

import java.util.List;
import org.ipen.miner.model.AbstractClassifier;
import org.ipen.miner.model.Model;

public class BooleanClassifier extends AbstractClassifier {
	public BooleanClassifier(List<CharSequence> data, Model model) {
		super(data, model);
	}

	@Override
	public float classify() {
		return model.classify(data);
	}
}
