package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.JNAWrapper;
import at.yrs4j.wrapper.impl.YMapIterImpl;
import at.yrs4j.yrslib.YrsMapIter;

public interface YMapIter extends YIterator<YMapEntry>, JNAWrapper<YrsMapIter> {
    static YMapIter wrap(YrsMapIter iter) {
        return new YMapIterImpl(iter);
    }
}
