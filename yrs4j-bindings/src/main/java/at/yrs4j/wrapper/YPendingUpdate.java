package at.yrs4j.wrapper;

import at.yrs4j.yrslib.YrsStateVector;
import at.yrs4j.yrslib.YrsStateVector;

public interface YPendingUpdate {
    void destroy();
    YrsStateVector getMissing();
    byte[] getUpdateV1();
    int getUpdateLen();
}
