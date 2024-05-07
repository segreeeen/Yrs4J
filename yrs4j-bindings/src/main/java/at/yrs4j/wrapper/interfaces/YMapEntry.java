package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.impl.YMapEntryImpl;
import at.yrs4j.yrslib.YrsMapEntry;

public interface YMapEntry {
    static YMapEntry wrap(YrsMapEntry mapEntry) {
        return new YMapEntryImpl(mapEntry);
    }

    void destroy();
}
