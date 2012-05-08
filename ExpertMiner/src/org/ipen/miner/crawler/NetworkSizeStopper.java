package org.ipen.miner.crawler;

import org.ipen.miner.collection.graph.Graph;

public class NetworkSizeStopper extends AbstractStopper {
	private Graph network;

	public NetworkSizeStopper(Graph graph, int limit) {
		super(limit);
		network = graph;
	}

	@Override
	public boolean stop() {
		return network.size() >= super.limit;
	}
}
