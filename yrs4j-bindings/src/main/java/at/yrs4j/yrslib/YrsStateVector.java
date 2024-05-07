package at.yrs4j.yrslib;

import com.sun.jna.Structure;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.ptr.LongByReference;

import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : libyrs.h:238</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class YrsStateVector extends Structure {
	/** Number of clients. It describes a length of both `client_ids` and `clocks` arrays. */
	public int entries_count;
	/**
	 * Array of unique client identifiers (length is given in `entries_count` field). Each client
<br>
	 * ID has corresponding clock attached, which can be found in `clocks` field under the same
<br>
	 * index.<br>
	 * C type : uint64_t*
	 */
	public LongByReference client_ids;
	/**
	 * Array of clocks (length is given in `entries_count` field) known for each client. Each clock
<br>
	 * has a corresponding client identifier attached, which can be found in `client_ids` field
<br>
	 * under the same index.<br>
	 * C type : uint32_t*
	 */
	public IntByReference clocks;
	public YrsStateVector() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("entries_count", "client_ids", "clocks");
	}
	/**
	 * @param entries_count Number of clients. It describes a length of both `client_ids` and `clocks` arrays.<br>
	 * @param client_ids Array of unique client identifiers (length is given in `entries_count` field). Each client
<br>
	 * ID has corresponding clock attached, which can be found in `clocks` field under the same
<br>
	 * index.<br>
	 * C type : uint64_t*<br>
	 * @param clocks Array of clocks (length is given in `entries_count` field) known for each client. Each clock
<br>
	 * has a corresponding client identifier attached, which can be found in `client_ids` field
<br>
	 * under the same index.<br>
	 * C type : uint32_t*
	 */
	public YrsStateVector(int entries_count, LongByReference client_ids, IntByReference clocks) {
		super();
		this.entries_count = entries_count;
		this.client_ids = client_ids;
		this.clocks = clocks;
	}
	public static class ByReference extends YrsStateVector implements Structure.ByReference {
		
	}

	public static class ByValue extends YrsStateVector implements Structure.ByValue {
		
	}
}
