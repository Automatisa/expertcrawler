package org.ipen.miner.crawler;

public interface Stopper {
	void setLimit(int t);
	int getLimit();
	boolean stop();
}
