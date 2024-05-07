package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.impl.YMapImpl;
import at.yrs4j.yrslib.YrsBranch;

public interface YMap {
    static YMap wrap(YrsBranch branch) {
        return new YMapImpl(branch);
    }

    int len(YTransaction transaction);
    void insert(YTransaction transaction, String key, YInput input);
    void remove(YTransaction transaction, String key);
    YOutput get(YTransaction transaction, String key);
    void removeAll(YTransaction transaction);
}
