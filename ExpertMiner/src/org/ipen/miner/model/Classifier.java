package org.ipen.miner.model;

import java.util.List;

public interface Classifier {
	List<CharSequence> data();
	Model model();
	//Class targetClass(); /*model.class();*/
	float classify();
}
