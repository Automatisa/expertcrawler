<pre>
package com.hypertry.web;<br>
<br>
public class Attribute {<br>
//immutable state<br>
protected final String name;<br>
protected final String value;<br>
<br>
//constructors<br>
protected Attribute(String name, String value) {<br>
this.name = name;<br>
this.value = value;<br>
}<br>
<br>
public String getName() {<br>
return name;<br>
}<br>
<br>
public String getValue() {<br>
return value;<br>
}<br>
<br>
/*<br>
public void setValue() {<br>
//<br>
}<br>
*/<br>
}<br>
<br>
package com.hypertry.web;<br>
<br>
public class ClassAttribute extends Attribute {<br>
private static final String NAME = "class";<br>
<br>
public ClassAttribute(String name, String value) {<br>
super(NAME, value);<br>
}<br>
}<br>
<br>
package com.hypertry.web;<br>
<br>
public abstract class Element {<br>
//immutable state<br>
private static final String name;<br>
private final String alias;<br>
private final String description;<br>
private final boolean hasContent;<br>
/*private final boolean isBlocking;*/<br>
/*private final boolean isInline;*/<br>
private final Tagging openingTagging;<br>
private final Tagging closingTagging;<br>
<br>
protected Element(String name, String alias, String description, boolean hasContent, Tagging openingTagging, Tagging closingTagging) {<br>
this.name = name;<br>
this.alias = alias;<br>
this.description = description;<br>
this.hasContent = hasContent;<br>
this.openingTagging = openingTagging;<br>
this.closingTagging = closingTagging;<br>
}<br>
<br>
public String write(Object content) {<br>
return content.toString();<br>
}<br>
<br>
public static class Tag {<br>
private final String name;<br>
private final String alias;<br>
private final String description;<br>
private final boolean hasContent;<br>
/*private final boolean isBlocking;*/<br>
/*private final boolean isInline;*/<br>
private final Tagging openingTagging;<br>
private final Tagging closingTagging;<br>
<br>
protected Tag(String name, String alias, String description, boolean hasContent, Tagging openingTagging, Tagging closingTagging) {<br>
this.name = name;<br>
this.alias = alias;<br>
this.description = description;<br>
this.hasContent = hasContent;<br>
this.openingTagging = openingTagging;<br>
this.closingTagging = closingTagging;<br>
}<br>
<br>
public String getName() {<br>
return name;<br>
}<br>
<br>
public String getAlias() {<br>
return alias;<br>
}<br>
<br>
public String getDescription() {<br>
return description;<br>
}<br>
<br>
public boolean hasContent() {<br>
return hasContent;<br>
}<br>
<br>
public Tagging getOpeningTagging() {<br>
return openingTagging;<br>
}<br>
<br>
public Tagging getClosingTagging() {<br>
return closingTagging;<br>
}<br>
<br>
/*passar set de atributos*/<br>
public String getOpeningTag() {<br>
//builder.append(NEW_LINE).append(OPEN_ANGLE_BRACKET).append(entity).append(CLOSE_ANGLE_BRACKET);<br>
//builder.append(NEW_LINE).append(OPEN_ANGLE_BRACKET).append(CLOSE_SLASH).append(entity).append(CLOSE_ANGLE_BRACKET);<br>
return null;<br>
}<br>
<br>
public enum Tagging {<br>
REQUIRED,<br>
OPTIONAL,<br>
FORBIDDEN<br>
}<br>
}<br>
<br>
//object interface<br>
@Override<br>
public String toString() {<br>
return name;<br>
}<br>
}<br>
<br>
package com.hypertry.web;<br>
<br>
import com.hypertry.design.Worker;<br>
import com.hypertry.design.Mutable;<br>
<br>
@Worker<br>
@Mutable<br>
public class Fetcher {<br>
//empty<br>
}<br>
<br>
package com.hypertry.web;<br>
<br>
public class Hypertext extends Element {<br>
//constant state<br>
private static final Tag DEFINITION = new Tag("html", "html", "", true, Element.Tagging.OPTIONAL, Element.Tagging.OPTIONAL);<br>
<br>
public Hypertext() {<br>
super("html", "html", "", true, Element.Tagging.OPTIONAL, Element.Tagging.OPTIONAL);<br>
}<br>
}<br>
<br>
package com.hypertry.web.html;<br>
<br>
//@Knower<br>
//@Immutable<br>
public class Descriptor {<br>
private final String name;<br>
private final String comment;<br>
<br>
protected Descriptor(String name, String comment) {<br>
this.name = name;<br>
this.comment = comment;<br>
}<br>
<br>
public String getName() {<br>
return name;<br>
}<br>
<br>
public String getComment() {<br>
return comment;<br>
}<br>
}<br>
<br>
package com.hypertry.web.html;<br>
<br>
public class ElementDescriptor extends Descriptor {<br>
private final String alias;<br>
private final boolean hasContent;<br>
/*private final boolean isBlocking;*/<br>
/*private final boolean isInline;*/<br>
private final Tagging openingTagging;<br>
private final Tagging closingTagging;<br>
<br>
public ElementDescriptor(String name, String alias, String comment, boolean hasContent, Tagging openingTagging, Tagging closingTagging) {<br>
super(name, comment);<br>
this.alias = alias;<br>
this.hasContent = hasContent;<br>
this.openingTagging = openingTagging;<br>
this.closingTagging = closingTagging;<br>
}<br>
<br>
public String getAlias() {<br>
return alias;<br>
}<br>
<br>
public boolean hasContent() {<br>
return hasContent;<br>
}<br>
<br>
public Tagging getOpeningTagging() {<br>
return openingTagging;<br>
}<br>
<br>
public Tagging getClosingTagging() {<br>
return closingTagging;<br>
}<br>
<br>
public enum Tagging {<br>
REQUIRED,<br>
OPTIONAL,<br>
FORBIDDEN<br>
}<br>
}<br>
<br>
package com.hypertry.web.html;<br>
<br>
public interface Member {<br>
public String getName();<br>
public String write();<br>
}<br>
<br>
package com.hypertry.web.html.attribute;<br>
<br>
public abstract class AbstractAttribute implements Attribute {<br>
//mutable state<br>
private Object value;<br>
<br>
@Override<br>
public String getName() {<br>
return getDescriptor().getName();<br>
}<br>
<br>
@Override<br>
public String write() {<br>
return getDescriptor().getName() + "=";<br>
}<br>
<br>
//object behaviour<br>
@Override<br>
public int hashCode() {<br>
return getDescriptor().getName().hashCode() ^ value.hashCode();<br>
}<br>
<br>
@Override<br>
public boolean equals(Object object) {<br>
if (object == this) {<br>
return true;<br>
}<br>
<br>
if (object == null) {<br>
return false;<br>
}<br>
<br>
if (!(object instanceof AbstractAttribute)) {<br>
return false;<br>
}<br>
<br>
return value.equals(((AbstractAttribute)object).value);<br>
}<br>
<br>
@Override<br>
public String toString() {<br>
return write();<br>
}<br>
<br>
protected abstract AttributeDescriptor getDescriptor();<br>
}<br>
<br>
package com.hypertry.web.html.attribute;<br>
<br>
import com.hypertry.web.html.Member;<br>
<br>
public interface Attribute extends Member {<br>
//<br>
}<br>
<br>
package com.hypertry.web.html.attribute;<br>
<br>
import com.hypertry.web.html.Descriptor;<br>
<br>
public class AttributeDescriptor extends Descriptor {<br>
/*<br>
private static final String elements = "All elements but BASE, BASEFONT, HEAD, HTML, META, PARAM, SCRIPT, STYLE, TITLE";<br>
private static final String type = "cdata";<br>
private static final String def = "#IMPLIED";<br>
private static final Boolean deprecated = false;<br>
private static final String dtd = "";<br>
*/<br>
<br>
public AttributeDescriptor(String name, String alias, String comment) {<br>
super(name, comment);<br>
}<br>
}<br>
<br>
package com.hypertry.web.html.attribute;<br>
<br>
import java.util.Objects;<br>
<br>
//@Knower<br>
//@Mutable<br>
public class ClassAttribute extends AbstractAttribute {<br>
//constant state<br>
private static final AttributeDescriptor DESCRIPTOR = new AttributeDescriptor("class", "class", "space-separated list of classes");<br>
<br>
//mutable state<br>
private String value;<br>
<br>
//constructors<br>
public ClassAttribute(String value) {<br>
Objects.requireNonNull(value);<br>
//validate value according to http://www.w3.org/TR/html401/types.html#type-cdata<br>
this.value = value;<br>
}<br>
<br>
//class behaviour<br>
public String getValue() {<br>
return value;<br>
}<br>
<br>
public String setValue(String value) {<br>
String oldValue = this.value;<br>
this.value = value;<br>
return oldValue;<br>
}<br>
<br>
@Override<br>
public String write() {<br>
return super.write() + value;<br>
}<br>
<br>
@Override<br>
protected AttributeDescriptor getDescriptor() {<br>
return DESCRIPTOR;<br>
}<br>
}<br>
<br>
package com.hypertry.web.html.attribute;<br>
<br>
import java.util.Objects;<br>
<br>
//@Knower<br>
//@Immutable<br>
public class Id {<br>
//constant state<br>
private static final String NAME = "id";<br>
private static final String COMMENT = "document-wide unique id";<br>
<br>
/*<br>
private static final String elements = "All elements but BASE, HEAD, HTML, META, SCRIPT, STYLE, TITLE";<br>
private static final String type = "id";<br>
private static final String def = "#IMPLIED";<br>
private static final Boolean deprecated = false;<br>
private static final String dtd = "";<br>
*/<br>
<br>
//immutable state<br>
private final String value;<br>
<br>
//constructors<br>
public Id(String value) {<br>
Objects.requireNonNull(value);<br>
//validate value according to http://www.w3.org/TR/html401/types.html#type-id<br>
this.value = value;<br>
}<br>
<br>
//id behaviour<br>
public String getName() {<br>
return NAME;<br>
}<br>
<br>
public String getValue() {<br>
return value;<br>
}<br>
<br>
public String write() {<br>
return NAME + "=" + value;<br>
}<br>
<br>
/*<br>
public String setValue(String value) {<br>
String oldValue = this.value;<br>
this.value = value;<br>
return oldValue;<br>
}<br>
*/<br>
<br>
//object behaviour<br>
@Override<br>
public int hashCode() {<br>
return NAME.hashCode() ^ value.hashCode();<br>
}<br>
<br>
@Override<br>
public boolean equals(Object object) {<br>
if (object == this) {<br>
return true;<br>
}<br>
<br>
if (object == null) {<br>
return false;<br>
}<br>
<br>
if (!(object instanceof Id)) {<br>
return false;<br>
}<br>
<br>
return value.equals(((Id)object).value);<br>
}<br>
<br>
@Override<br>
public String toString() {<br>
return write();<br>
}<br>
}<br>
<br>
package com.hypertry.web.html.element;<br>
<br>
import com.hypertry.web.html.Member;<br>
import com.hypertry.web.html.attribute.Attribute;<br>
import java.util.ArrayList;<br>
import java.util.List;<br>
<br>
public class Anchor {<br>
//constant state<br>
private static final String NAME = "a";<br>
private static final String ALIAS = "anchor";<br>
private static final String DESCRIPTION = "";<br>
private static final boolean CONTENT = true;<br>
/*private static final boolean isBlocking;*/<br>
/*private static final boolean isInline;*/<br>
private static final Tagging OPENING_TAGGING = Tagging.REQUIRED;<br>
private static final Tagging CLOSING_TAGGING = Tagging.REQUIRED;<br>
<br>
//immutable state<br>
private List<Member> members = new ArrayList<>();<br>
private List<Attribute> attributes = new ArrayList<>();<br>
<br>
//anchor behaviour<br>
public String getName() {<br>
return NAME;<br>
}<br>
<br>
public String getAlias() {<br>
return ALIAS;<br>
}<br>
<br>
public String getDescription() {<br>
return DESCRIPTION;<br>
}<br>
<br>
public boolean content() {<br>
return CONTENT;<br>
}<br>
<br>
public Tagging getOpeningTagging() {<br>
return OPENING_TAGGING;<br>
}<br>
<br>
public Tagging getClosingTagging() {<br>
return CLOSING_TAGGING;<br>
}<br>
<br>
public enum Tagging {<br>
REQUIRED,<br>
OPTIONAL,<br>
FORBIDDEN<br>
}<br>
<br>
public add() {<br>
<br>
}<br>
}<br>
<br>
package com.hypertry.web.html.element;<br>
<br>
import com.hypertry.web.html.Member;<br>
<br>
public interface Element extends Member {<br>
//<br>
}<br>
<br>
package com.hypertry.web.html.element;<br>
<br>
import com.hypertry.web.html.Member;<br>
import com.hypertry.web.html.attribute.Attribute;<br>
import java.util.ArrayList;<br>
import java.util.List;<br>
<br>
public class Hypertext {<br>
//constant state<br>
private static final String NAME = "html";<br>
private static final String ALIAS = "hypertext";<br>
private static final String DESCRIPTION = "document root element";<br>
private static final boolean CONTENT = true;<br>
/*private static final boolean isBlocking;*/<br>
/*private static final boolean isInline;*/<br>
private static final Tagging OPENING_TAGGING = Tagging.OPTIONAL;<br>
private static final Tagging CLOSING_TAGGING = Tagging.OPTIONAL;<br>
<br>
//immutable state<br>
private List<Member> members = new ArrayList<>();<br>
private List<Attribute> attributes = new ArrayList<>();<br>
<br>
//anchor behaviour<br>
public String getName() {<br>
return NAME;<br>
}<br>
<br>
public String getAlias() {<br>
return ALIAS;<br>
}<br>
<br>
public String getDescription() {<br>
return DESCRIPTION;<br>
}<br>
<br>
public boolean content() {<br>
return CONTENT;<br>
}<br>
<br>
public Tagging getOpeningTagging() {<br>
return OPENING_TAGGING;<br>
}<br>
<br>
public Tagging getClosingTagging() {<br>
return CLOSING_TAGGING;<br>
}<br>
<br>
public enum Tagging {<br>
REQUIRED,<br>
OPTIONAL,<br>
FORBIDDEN<br>
}<br>
<br>
public Hypertext add(Head head) {<br>
members.add(head);<br>
return this;<br>
}<br>
<br>
public Hypertext add(Head head) {<br>
members.add(head);<br>
return this;<br>
}<br>
<br>
public Hypertext add(Body body) {<br>
members.add(head);<br>
return this;<br>
}<br>
}<br>
</pre>