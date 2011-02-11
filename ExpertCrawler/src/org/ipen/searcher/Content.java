package org.ipen.searcher;

import java.util.HashSet;

public class Content<M> {
	private String source;
	private String label;
	private M media;
	private float relevance;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public M getMedia() {
		return media;
	}

	public void setMedia(M media) {
		this.media = media;
	}

	public float getRelevance() {
		return relevance;
	}

	public void setRelevance(float relevance) {
		this.relevance = relevance;
	}
}