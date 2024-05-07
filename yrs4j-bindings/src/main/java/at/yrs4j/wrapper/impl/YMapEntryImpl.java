package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.YMapEntry;
import at.yrs4j.yrslib.YrsMapEntry;

public class YMapEntryImpl extends AbstractJNAWrapper<YrsMapEntry> implements YMapEntry {

    public YMapEntryImpl(YrsMapEntry wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public void destroy() {
        Yrs4J.YRS_INSTANCE.ymap_entry_destroy(super.wrappedObject);
    }
}
