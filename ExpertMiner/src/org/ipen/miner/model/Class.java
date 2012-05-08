package org.ipen.miner.model;

import java.util.Set;

public interface Class {
	CharSequence name();
	CharSequence description();
	Set<CharSequence> lexicon();
}
