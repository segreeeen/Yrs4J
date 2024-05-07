package at.yrs4j.yrslib;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : libyrs.h:319</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class YrsPendingUpdate extends Structure {
	/**
	 * A state vector that informs about minimal client clock values that need to be satisfied
<br>
	 * in order to successfully apply current update.<br>
	 * C type : YrsStateVector
	 */
	public YrsStateVector missing;
	/**
	 * Update data stored in lib0 v1 format.<br>
	 * C type : char*
	 */
	public Pointer update_v1;
	/** Length of `update_v1` payload. */
	public int update_len;
	public YrsPendingUpdate() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("missing", "update_v1", "update_len");
	}
	/**
	 * @param missing A state vector that informs about minimal client clock values that need to be satisfied
<br>
	 * in order to successfully apply current update.<br>
	 * C type : YrsStateVector<br>
	 * @param update_v1 Update data stored in lib0 v1 format.<br>
	 * C type : char*<br>
	 * @param update_len Length of `update_v1` payload.
	 */
	public YrsPendingUpdate(YrsStateVector missing, Pointer update_v1, int update_len) {
		super();
		this.missing = missing;
		this.update_v1 = update_v1;
		this.update_len = update_len;
	}
	public static class ByReference extends YrsPendingUpdate implements Structure.ByReference {
		
	}

	public static class ByValue extends YrsPendingUpdate implements Structure.ByValue {
		
	}
}
