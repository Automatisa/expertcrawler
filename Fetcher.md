<pre>
//TODO verificar http://nadeausoftware.com/node/73<br>
<br>
package com.miner.web;<br>
<br>
import java.io.BufferedReader;<br>
import java.io.IOException;<br>
import java.io.InputStreamReader;<br>
import java.nio.charset.Charset;<br>
import java.nio.charset.UnsupportedCharsetException;<br>
import java.nio.charset.IllegalCharsetNameException;<br>
import java.net.URLConnection;<br>
import com.miner.design.marker.Mutable;<br>
import com.miner.design.marker.Worker;<br>
import com.miner.design.Resettable;<br>
<br>
public class Fetcher implements Resettable, Worker, Mutable {<br>
//immutable state<br>
private final int connectTimeout;<br>
private final int readTimeout;<br>
private final Charset charset;<br>
<br>
//mutable state<br>
private String html;<br>
<br>
//constructors<br>
public Fetcher() {<br>
this(10000, 10000, Charset.defaultCharset());<br>
}<br>
<br>
public Fetcher(int connectTimeout, int readTimeout, Charset charset) {<br>
if (charset == null) {<br>
throw new NullPointerException("Invalid null charset.");<br>
}<br>
<br>
if (connectTimeout < 0 || readTimeout < 0) {<br>
throw new IllegalArgumentException("Invalid timeout(s) value.");<br>
}<br>
<br>
this.connectTimeout = connectTimeout;<br>
this.readTimeout = readTimeout;<br>
this.charset = charset;<br>
}<br>
<br>
//fetcher interface<br>
public int getConnectTimeout() {<br>
return connectTimeout;<br>
}<br>
<br>
public int getReadTimeout() {<br>
return readTimeout;<br>
}<br>
<br>
public Charset getDefaultCharset() {<br>
return charset;<br>
}<br>
<br>
public String getHTML() {<br>
if (html == null) {<br>
throw new IllegalStateException("Invalid fetcher state.");<br>
}<br>
<br>
return html;<br>
}<br>
<br>
public boolean fetch(Resource resource) {<br>
if (resource == null) {<br>
reset();<br>
return false;<br>
}<br>
<br>
try {<br>
URLConnection connection = resource.getLocator().newURL().openConnection();<br>
connection.addRequestProperty("User-Agent", "Mozilla/4.76");<br>
connection.setConnectTimeout(connectTimeout);<br>
connection.setReadTimeout(readTimeout);<br>
<br>
StringBuilder content = new StringBuilder();<br>
<br>
try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), getCharset(connection)))) {<br>
String line;<br>
<br>
while ((line = bufferedReader.readLine()) != null) {<br>
content.append(line);<br>
}<br>
}<br>
<br>
//create a copy, don't share the string value array<br>
this.html = content.toString();<br>
return true;<br>
} catch (IOException | IllegalArgumentException exception) {<br>
this.html = null;<br>
return false;<br>
}<br>
}<br>
<br>
//resettable interface<br>
@Override<br>
public void reset() {<br>
html = null;<br>
}<br>
<br>
//miscellaneous<br>
private Charset getCharset(URLConnection connection) {<br>
String header = connection.getHeaderField("Content-Type");<br>
<br>
if (header == null) {<br>
return charset;<br>
}<br>
<br>
int index = header.indexOf("charset");<br>
<br>
if (index == -1) {<br>
return charset;<br>
}<br>
<br>
try {<br>
return Charset.forName(header.substring(index + 8));<br>
} catch (UnsupportedCharsetException | IllegalCharsetNameException exception) {<br>
return charset;<br>
}<br>
}<br>
}<br>
</pre>