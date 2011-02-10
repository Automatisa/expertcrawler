package org.ipen.searcher;

import java.util.HashSet;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.MalformedURLException;

public class Page {
	private String pageUrl;
	private String pageHtml;
	private HashSet pageLinks;
	private boolean isConnected = false;
	private boolean isFetched = false;
	private URL urlAddress;

	public Page(String url) {
		this.pageUrl = url;
		connectPage(url, false);
		if (this.isConnected) {
			fetchPage();
			if (this.isFetched) {
				fetchLinks();
			}
		}
	}

	public Page(String url, boolean hasProxy) {
		this.pageUrl = url;
		connectPage(url, hasProxy);
		if (this.isConnected) {
			fetchPage();
			if (this.isFetched) {
				fetchLinks();
			}
		}
	}

	public String getPageUrl() {
		return this.pageUrl;
	}

	public String getPageHtml() {
		return this.pageHtml;
	}

	public HashSet getPageLinks() {
		return this.pageLinks;
	}

	public boolean getPageConnected() {
		return this.isConnected;
	}

	public boolean getPageFetched() {
		return this.isFetched;
	}

	private void connectPage(String url, boolean hasProxy) {
		try {
			if (hasProxy) {
				System.setProperty("http.proxyHost", "10.105.160.5");
				System.setProperty("http.proxyPort", "8080");

				Authenticator.setDefault(new Authenticator() {
					@Override protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication("thiago_reis", "3DUFANED".toCharArray());
					}
				});
			}
			this.urlAddress = new URL(url);
			this.urlAddress.openConnection();
			this.isConnected = true;
		}
		catch (MalformedURLException e) {
			this.isConnected = false;
		}
		catch (IOException e) {
			this.isConnected = false;
		}
	}

	private void fetchPage() {
		try {
			InputStreamReader input = new InputStreamReader(urlAddress.openStream());
			BufferedReader reader = new BufferedReader(input);
			StringBuilder pageBuffer = new StringBuilder();

			String line = new String();
			while ((line = reader.readLine()) != null) {
				pageBuffer.append(line);
			}

			this.pageHtml = pageBuffer.toString();
			this.isFetched = true;
		}
		catch (IOException e) {
			this.isFetched = false;
		}
	}

	private void fetchLinks() {
		Pattern linkPattern = Pattern.compile("<a\\s+href\\s*=\\s*\"?(.*?)[\"|>]", Pattern.CASE_INSENSITIVE);
		Matcher linkMatcher = linkPattern.matcher(this.pageHtml);

		// Create list of link matches.
		this.pageLinks = new HashSet();
		while (linkMatcher.find()) {
			String link = linkMatcher.group(1).trim();

			// Skip empty links/links that are just page anchors/mailto links/JavaScript links
			if (link.length() < 1 && link.charAt(0) == '#' && link.indexOf("mailto:") != -1 && link.toLowerCase().indexOf("javascript") != -1) {
				continue;
			}

			// Prefix absolute and relative URLs if necessary.
			if (link.indexOf("://") == -1) {
				// Handle absolute URLs.
				if (link.charAt(0) == '/') {
					link = "http://" + urlAddress.getHost() + link;
				}
				// Handle relative URLs.
				else {
					String file = urlAddress.getFile();
					if (file.indexOf('/') == -1) {
						link = "http://" + urlAddress.getHost() + "/" + link;
					}
					else {
						String path = file.substring(0, file.lastIndexOf('/') + 1);
						link = "http://" + urlAddress.getHost() + path + link;
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
			this.pageLinks.add(link);
		}
	}
}