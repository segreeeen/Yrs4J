package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.impl.YArrayImpl;
import at.yrs4j.yrslib.YrsBranch;

public interface YArray extends Iterable<YOutput> {
    int len();
    YOutput get(YTransaction transaction, int index);
    void insertRange(YTransaction transaction, int index, YInput[] items);
    void removeRange(YTransaction transaction, int index, int len);
    void move(YTransaction transaction, int source, int target);
    YArrayIter iter(YTransaction transaction);
    static YArray wrap(YrsBranch branch) {
        return new YArrayImpl(branch);
    }
    static YArray createWithDocAndName(YDoc doc, String name) {
        return new YArrayImpl(doc, name);
    }
    void setTransaction(YTransaction transaction);

}
