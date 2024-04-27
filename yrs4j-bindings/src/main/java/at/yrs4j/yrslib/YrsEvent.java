package at.yrs4j.yrslib;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : libyrs.h:468</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class YrsEvent extends Structure {
	/**
	 * Tag describing, which shared type emitted this event.
<br>
	 * <br>
	 * - [Y_TEXT] for pointers to `YText` data types.
<br>
	 * - [Y_ARRAY] for pointers to `YArray` data types.
<br>
	 * - [Y_MAP] for pointers to `YMap` data types.
<br>
	 * - [Y_XML_ELEM] for pointers to `YXmlElement` data types.
<br>
	 * - [Y_XML_TEXT] for pointers to `YXmlText` data types.
	 */
	public byte tag;
	/**
	 * A nested event type, specific for a shared data type that triggered it. Type of an
<br>
	 * event can be verified using `tag` field.<br>
	 * C type : YrsEventContent
	 */
	public YrsEventContent content;
	public YrsEvent() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("tag", "content");
	}
	/**
	 * @param tag Tag describing, which shared type emitted this event.
<br>
	 * <br>
	 * - [Y_TEXT] for pointers to `YText` data types.
<br>
	 * - [Y_ARRAY] for pointers to `YArray` data types.
<br>
	 * - [Y_MAP] for pointers to `YMap` data types.
<br>
	 * - [Y_XML_ELEM] for pointers to `YXmlElement` data types.
<br>
	 * - [Y_XML_TEXT] for pointers to `YXmlText` data types.<br>
	 * @param content A nested event type, specific for a shared data type that triggered it. Type of an
<br>
	 * event can be verified using `tag` field.<br>
	 * C type : YrsEventContent
	 */
	public YrsEvent(byte tag, YrsEventContent content) {
		super();
		this.tag = tag;
		this.content = content;
	}
	public static class ByReference extends YrsEvent implements Structure.ByReference {
		
	}

	public static class ByValue extends YrsEvent implements Structure.ByValue {
		
	}
}
