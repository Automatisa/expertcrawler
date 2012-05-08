package org.ipen.miner.model;

import java.util.List;

public interface Model {
	Class targetClass();
	Classifier classifier(List<CharSequence> data);
	//float classify(List<CharSequence> data); /*classifier.classify(StringSequence data);*/
}
