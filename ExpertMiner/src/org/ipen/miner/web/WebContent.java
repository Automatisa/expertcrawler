package org.ipen.miner.web;

import java.io.Serializable;
import java.util.HashSet;

public class WebContent extends AbstractContent implements Cloneable, Serializable {
	public WebContent(String source) {
		super.content = null;
		super.source = source;
		super.collector = new WebCollector(source, this);
		super.targets = null;
	}

	//construtor chamado quando o conteudo é criado por um coletor (somente dentro do package)
	//pelo metodo collect do coletor que retorna o conteudo
	WebContent(String source, String content, HashSet<WebContent> targets) {
		super.content = content;
		super.source = source;
		super.collector = null;
		super.targets = targets;
		createdFromCollector = true;
	}
}
