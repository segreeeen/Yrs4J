package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.YInput;
import at.yrs4j.wrapper.interfaces.YMap;
import at.yrs4j.wrapper.interfaces.YOutput;
import at.yrs4j.wrapper.interfaces.YTransaction;
import at.yrs4j.yrslib.YrsBranch;
import at.yrs4j.yrslib.YrsInput;
import at.yrs4j.yrslib.YrsOutput;
import at.yrs4j.yrslib.YrsTransaction;

public class YMapImpl extends AbstractJNAWrapper<YrsBranch> implements YMap {

    public YMapImpl(YrsBranch wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public int len(YTransaction transaction) {
        YrsTransaction txn = ((YTransactionImpl) transaction).getWrappedObject();

        return Yrs4J.YRS_INSTANCE.ymap_len(wrappedObject, txn);
    }

    @Override
    public void insert(YTransaction transaction, String key, YInput input) {
        YrsTransaction txn = ((YTransactionImpl) transaction).getWrappedObject();
        YrsInput in = ((YInputImpl) input).getWrappedObject();

        Yrs4J.YRS_INSTANCE.ymap_insert(wrappedObject, txn, key, in);
    }

    @Override
    public void remove(YTransaction transaction, String key) {
        YrsTransaction txn = ((YTransactionImpl) transaction).getWrappedObject();
        Yrs4J.YRS_INSTANCE.ymap_remove(wrappedObject, txn, key);
    }

    @Override
    public YOutput get(YTransaction transaction, String key) {
        YrsTransaction txn = ((YTransactionImpl) transaction).getWrappedObject();
        YrsOutput out = Yrs4J.YRS_INSTANCE.ymap_get(wrappedObject, txn, key);
        return YOutput.wrap(out);
    }

    @Override
    public void removeAll(YTransaction transaction) {
        YrsTransaction txn = ((YTransactionImpl) transaction).getWrappedObject();
        Yrs4J.YRS_INSTANCE.ymap_remove_all(wrappedObject, txn);
    }
}
