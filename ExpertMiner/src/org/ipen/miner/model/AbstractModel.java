package org.ipen.miner.model;

public abstract class AbstractModel implements Model {
	protected Class targetClass;
	protected Classifier classifier;

	@Override
	public Class targetClass() {
		return targetClass;
	}
}
