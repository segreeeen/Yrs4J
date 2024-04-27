package at.yrs4j.yrslib;

import com.sun.jna.Pointer;
import com.sun.jna.PointerType;

// This is a simple wrapper for what is effectively an opaque pointer in C
public class YrsTransaction extends PointerType {
    public YrsTransaction() {
        super();
    }

    public YrsTransaction(Pointer p) {
        super(p);
    }
}