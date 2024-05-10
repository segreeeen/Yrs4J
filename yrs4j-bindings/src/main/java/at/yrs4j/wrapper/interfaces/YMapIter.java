package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.impl.YMapIterImpl;
import at.yrs4j.yrslib.YrsMapIter;

public interface YMapIter extends YIterable<YMapEntry> {
    static YMapIter wrap(YrsMapIter iter) {
        return new YMapIterImpl(iter);
    }
}
