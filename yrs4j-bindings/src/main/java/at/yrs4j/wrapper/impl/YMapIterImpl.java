package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.wrapper.AbstractDestroyableJNAWrapper;
import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.YMapEntry;
import at.yrs4j.wrapper.interfaces.YMapIter;
import at.yrs4j.yrslib.YrsMapEntry;
import at.yrs4j.yrslib.YrsMapIter;

public class YMapIterImpl extends AbstractDestroyableJNAWrapper<YrsMapIter> implements YMapIter {
    public YMapIterImpl(YrsMapIter wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public void destroy() {
        Yrs4J.YRS_INSTANCE.ymap_iter_destroy(wrappedObject);
        super.destroy();
    }

    @Override
    public YMapEntry next() {
        YrsMapEntry output = Yrs4J.YRS_INSTANCE.ymap_iter_next(wrappedObject);
        if (output != null) {
            return YMapEntry.wrap(output);
        }

        return null;
    }
}
