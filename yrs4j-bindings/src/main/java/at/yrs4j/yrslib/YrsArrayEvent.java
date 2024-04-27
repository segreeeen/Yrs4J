package at.yrs4j.yrslib;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : libyrs.h:415</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class YrsArrayEvent extends Structure {
	/** C type : const void* */
	public Pointer inner;
	/** C type : const YrsTransactionMut* */
	public YrsTransactionMut.ByReference txn;
	public YrsArrayEvent() {
		super();
	}
	protected List<String> getFieldOrder() {
		return Arrays.asList("inner", "txn");
	}
	/**
	 * @param inner C type : const void*<br>
	 * @param txn C type : const YrsTransactionMut*
	 */
	public YrsArrayEvent(Pointer inner, YrsTransactionMut.ByReference txn) {
		super();
		this.inner = inner;
		this.txn = txn;
	}
	public static class ByReference extends YrsArrayEvent implements Structure.ByReference {
		
	}

	public static class ByValue extends YrsArrayEvent implements Structure.ByValue {
		
	}
}
