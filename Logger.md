<pre>
package com.log;<br>
<br>
import java.util.logging.Level;<br>
import java.util.logging.Logger;<br>
<br>
public interface Loggable {<br>
Logger getLogger();<br>
Level getLevel();<br>
}<br>
<br>
package com.log;<br>
<br>
import java.util.logging.Level;<br>
import java.util.logging.Logger;<br>
<br>
public class LoggableException extends Exception implements Loggable {<br>
//static state<br>
private static final Level DEFAULT_LEVEL = Level.WARNING;<br>
private static final Object a = System.in;<br>
<br>
//immutable state<br>
private final Logger logger;<br>
private final Level level;<br>
<br>
//constructors<br>
public LoggableException(Logger logger, Level level) {<br>
super();<br>
this.logger = logger;<br>
this.level = level;<br>
logger.log(level, null, this);<br>
Logger.getLogger(LoggableException.class.getName());<br>
}<br>
<br>
public LoggableException(Logger logger, Level level, String message) {<br>
super(message);<br>
this.logger = logger;<br>
this.level = level;<br>
logger.log(level, message, this);<br>
}<br>
<br>
public LoggableException(Logger logger, Level level, String message, Throwable cause) {<br>
super(message, cause);<br>
this.logger = logger;<br>
this.level = level;<br>
logger.log(level, message, this);<br>
}<br>
<br>
public LoggableException(Logger logger, Level level, Throwable cause) {<br>
super(cause);<br>
this.logger = logger;<br>
this.level = level;<br>
logger.log(level, null, this);<br>
}<br>
<br>
protected LoggableException(Logger logger, Level level, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {<br>
super(message, cause, enableSuppression, writableStackTrace);<br>
this.logger = logger;<br>
this.level = level;<br>
logger.log(level, message, this);<br>
}<br>
<br>
//loggable interface<br>
@Override<br>
public Logger getLogger() {<br>
return logger;<br>
}<br>
<br>
@Override<br>
public Level getLevel() {<br>
return level;<br>
}<br>
}<br>
<br>
package com.log;<br>
<br>
public class LoggableExceptionFactory {<br>
<br>
}<br>
<br>
package com.log;<br>
<br>
import java.io.IOException;<br>
import java.util.logging.Level;<br>
import java.util.logging.Logger;<br>
<br>
//decorates a cause exception to add logging functionality or defines a root exception<br>
public class LoggableIOException extends IOException implements Loggable {<br>
//immutable state<br>
private final Logger logger;<br>
private final Level level;<br>
<br>
//constructors<br>
public LoggableIOException(Logger logger, Level level, IOException cause) {<br>
super(cause.getMessage() + " [logger name: " + logger.getName() + ", logger level: " + logger.getLevel() + ", exception level: " + level.getName() + "]", cause);<br>
this.logger = logger;<br>
this.level = level;<br>
logger.log(level, cause.getMessage(), this);<br>
}<br>
<br>
//loggable interface<br>
@Override<br>
public Logger getLogger() {<br>
return logger;<br>
}<br>
<br>
@Override<br>
public Level getLevel() {<br>
return level;<br>
}<br>
}<br>
<br>
package com.log;<br>
<br>
import java.io.IOException;<br>
import static java.lang.System.out;<br>
import java.util.logging.Filter;<br>
import java.util.logging.LoggingMXBean;<br>
import java.util.logging.LoggingPermission;<br>
<br>
import java.util.logging.ErrorManager;<br>
import java.util.logging.Formatter;<br>
import java.util.logging.SimpleFormatter;<br>
import java.util.logging.XMLFormatter;<br>
import java.util.logging.Handler;<br>
import java.util.logging.MemoryHandler;<br>
import java.util.logging.StreamHandler;<br>
import java.util.logging.ConsoleHandler;<br>
import java.util.logging.SocketHandler;<br>
import java.util.logging.FileHandler;<br>
import java.util.logging.Level;<br>
import java.util.logging.Logger;<br>
import java.util.logging.LogManager;<br>
import java.util.logging.LogRecord;<br>
<br>
/*<br>
http://docs.oracle.com/javase/tutorial/essential/exceptions/index.html<br>
<br>
http://docs.oracle.com/javase/8/docs/technotes/guides/logging/overview.html<br>
http://www.vogella.com/tutorials/Logging/article.html<br>
http://tutorials.jenkov.com/java-logging/index.html<br>
http://tutorials.jenkov.com/java-exception-handling/logging-where-to-log-exceptions.html<br>
https://today.java.net/article/2006/04/04/exception-handling-antipatterns<br>
<br>
logar exception no momento que ele ocorre<br>
relançar exception e logar ela mais acima<br>
guardar exceptions e logar depois<br>
criar exceptions especificas e logar no construtor dela<br>
<br>
<br>
<br>
<br>
<br>
how to logging:<br>
handler<br>
formatter<br>
level (throw/catch level)<br>
<br>
when to logging:<br>
throw or catch?		throw: LoggableException, catch: Logger<br>
lower or higher?	lower: LoggableException, higher: Logger<br>
<br>
Application										catch and log checkeds exceptions (throws LoggableException?)<br>
Searcher.search								throw checked exception<br>
HypertextFactory.make					throw checked exception (DecoderException, FetcherException, ParserException)<br>
LocatorFactory.make					throw checked exception (DecoderException)<br>
Decoder.decode					throw checked exception<br>
URISyntaxException<br>
MalformedURLException<br>
<br>
Fetcher.fetch						throw checked exception<br>
IOException<br>
IllegalArgumentException<br>
<br>
Parser.parse						throw checked exception<br>
IOException<br>
*/<br>
<br>
public class Tester {<br>
public static void main(String[] args) throws IOException, SecurityException {<br>
//Logger logger = Logger.getLogger(Tester.class.getName());<br>
Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);<br>
<br>
FileHandler txtHandler = new FileHandler("C:\\Users\\trurktt\\Documents\\log.txt");<br>
FileHandler xmlHandler = new FileHandler("C:\\Users\\trurktt\\Documents\\log.xml");<br>
<br>
Formatter txtFormatter = new SimpleFormatter();<br>
Formatter xmlFormatter = new XMLFormatter();<br>
<br>
txtHandler.setFormatter(txtFormatter);<br>
xmlHandler.setFormatter(xmlFormatter);<br>
<br>
logger.addHandler(txtHandler);<br>
logger.addHandler(xmlHandler);<br>
<br>
logger.setLevel(Level.ALL);<br>
logger.info("teste");<br>
//logger.log(Level.SEVERE, "teste", null);<br>
<br>
//out.println(logger.getName());<br>
//out.println(logger.getParent().getName());<br>
LogManager.getLogManager();<br>
}<br>
}<br>
</pre>