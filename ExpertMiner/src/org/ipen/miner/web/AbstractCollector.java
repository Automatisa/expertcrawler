package org.ipen.miner.web;

public abstract class AbstractCollector implements Collector {
	protected Object source;
	protected boolean collected = false;
	protected AbstractContent content;
	protected boolean createdFromContent = false;

	@Override
	public boolean isCollected() {
		return collected;
	}

	@Override
	public Object clone() {
		AbstractCollector result = null;

		try {
			result = (AbstractCollector)super.clone();
		} catch (CloneNotSupportedException e) {
			// assert false;
		}

		result.source = source;
		result.collected = false;
		return result;
	}
}
