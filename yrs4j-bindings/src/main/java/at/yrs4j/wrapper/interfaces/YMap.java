package at.yrs4j.wrapper.interfaces;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.wrapper.JNAWrapper;
import at.yrs4j.wrapper.impl.YDocImpl;
import at.yrs4j.wrapper.impl.YMapImpl;
import at.yrs4j.yrslib.YrsBranch;
import at.yrs4j.yrslib.YrsDoc;

public interface YMap extends YIterable<YMapEntry>, JNAWrapper<YrsBranch> {
    static YMap wrap(YrsBranch branch) {
        return new YMapImpl(branch);
    }

    static YMap createWithDocAndName(YDoc doc, String name) {
        YrsDoc yrsDoc = doc.getWrappedObject();

        return YMap.wrap(Yrs4J.YRS_INSTANCE.ymap(yrsDoc, name));
    }

    int len(YTransaction transaction);
    void insert(YTransaction transaction, String key, YInput input);
    void remove(YTransaction transaction, String key);
    YOutput get(YTransaction transaction, String key);
    void removeAll(YTransaction transaction);
    YMapIter iter(YTransaction txn);

    void setTransaction(YTransaction transaction);
}
