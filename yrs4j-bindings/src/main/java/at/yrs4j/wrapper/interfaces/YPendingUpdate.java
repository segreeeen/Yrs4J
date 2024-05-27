package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.Destroyable;
import at.yrs4j.yrslib.YrsStateVector;

public interface YPendingUpdate extends Destroyable {
    YrsStateVector getMissing();
    byte[] getUpdateV1();
    int getUpdateLen();
}
