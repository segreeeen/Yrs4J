package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.Destroyable;
import at.yrs4j.wrapper.JNAWrapper;
import at.yrs4j.wrapper.impl.YMapEntryImpl;
import at.yrs4j.yrslib.YrsMapEntry;

public interface YMapEntry extends Destroyable, JNAWrapper<YrsMapEntry> {
    static YMapEntry wrap(YrsMapEntry mapEntry) {
        return new YMapEntryImpl(mapEntry);
    }

    String getKey();
    YOutput getValue();
}
