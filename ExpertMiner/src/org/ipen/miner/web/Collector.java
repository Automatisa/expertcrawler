package org.ipen.miner.web;

public interface Collector {
	Content collect();
	boolean isCollected();
	Object clone();
}
