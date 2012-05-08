package org.ipen.miner.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebCollector extends AbstractCollector implements Cloneable, Serializable {
	private static final int TIMEOUT = 10000;
	private boolean hasProxy = false;
	private boolean failed = false;

	public WebCollector(String source) {
		super.source = source;
	}

	//construtor chamado quando o coletor é criado por um conteudo (somente dentro do package)
	//pelo construtor publico do conteudo para efetuar a referencia cruzada
	//o conteudo deriva o trabalho de coleta para o seu coletor interno
	//e o coletor interno atualiza diretamente os campos do conteudo
	WebCollector(String source, WebContent content) {
		super.source = source;
		super.content = content;
		createdFromContent = true;
	}

	@Override
	public WebContent collect() {
		URL url = connect((String)source);

		if (!failed) {
			String html = fetchHTML(url);

			if (!failed) {
				HashSet<WebContent> links = fetchLinks(url, html);

				if (!failed) {
					if (createdFromContent) {
						content.content = html;
						content.targets = links;
						collected = true;
						return (WebContent)content;
					} else {
						collected = true;
						return new WebContent((String)source, html, links);
					}
				}
			}
		}

		collected = false;
		return null;
	}

	private URL connect(String source) {
		try {
			if (hasProxy) {
				System.setProperty("http.proxyHost", "10.105.160.5");
				System.setProperty("http.proxyPort", "8080");

				Authenticator.setDefault(
					new Authenticator() {
						@Override
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication("thiago_reis", "3DUFANED".toCharArray());
						}
					}
				);
			}

			URL url = new URL(source);
			url.openConnection().setConnectTimeout(TIMEOUT);
			failed = false;
			return url;
		} catch (MalformedURLException e) {
			failed = true;
			return null;
		} catch (IOException e) {
			failed = true;
			return null;
		}
	}

	private String fetchHTML(URL url) {
		try {
			InputStreamReader input = new InputStreamReader(url.openStream());
			BufferedReader reader = new BufferedReader(input);
			StringBuilder pageBuffer = new StringBuilder();

			String line;
			while ((line = reader.readLine()) != null) {
				pageBuffer.append(line);
			}

			failed = false;
			return pageBuffer.toString();
		} catch (IOException e) {
			failed = true;
			return null;
		}
	}

	private HashSet<WebContent> fetchLinks(URL url, String html) {
		Pattern pattern = Pattern.compile("<a\\s+href\\s*=\\s*\"?(.*?)[\"|>]", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(html);
		HashSet<WebContent> links = new HashSet<>();

		//Create list of link matches.
		//super.frontier = new HashSet();
		while (matcher.find()) {
			String link = matcher.group(1).trim();

			// Skip empty links/links that are just page anchors/mailto links/JavaScript links
			if (link.length() < 1 || link.charAt(0) == '#' || link.indexOf("mailto:") != -1 || link.toLowerCase().indexOf("javascript") != -1) {
				continue;
			}

			// Prefix absolute and relative URLs if necessary.
			if (link.indexOf("://") == -1) {
				// Handle absolute URLs.
				if (link.charAt(0) == '/') {
					link = "http://" + url.getHost() + link;
				}
				// Handle relative URLs.
				else {
					String file = url.getFile();
					if (file.indexOf('/') == -1) {
						link = "http://" + url.getHost() + "/" + link;
					}
					else {
						String path = file.substring(0, file.lastIndexOf('/') + 1);
						link = "http://" + url.getHost() + path + link;
					}
				}
			}

			// Remove anchors from link.
			int index = link.indexOf('#');
			if (index != -1) {
				link = link.substring(0, index);
			}

			// Remove leading "www" from URL's host if present.
			index = link.indexOf("://www.");
			if (index != -1) {
				link = link.substring(0, index + 3) + link.substring(index + 7);
			}

			// Add link to list.
			links.add(new WebContent(link));
		}
		failed = false;
		return links;
	}
}
