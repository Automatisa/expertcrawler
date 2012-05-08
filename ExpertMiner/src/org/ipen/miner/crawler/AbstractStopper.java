package org.ipen.miner.crawler;

public abstract class AbstractStopper implements Stopper {
	protected int limit;

	protected AbstractStopper(int limit) {
		this.limit = limit;
	}

	@Override
	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public int getLimit() {
		return limit;
	}
}
