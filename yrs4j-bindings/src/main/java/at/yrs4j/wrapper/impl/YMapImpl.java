package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.*;
import at.yrs4j.yrslib.*;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class YMapImpl extends AbstractJNAWrapper<YrsBranch> implements YMap {

    private YTransaction transaction;

    public YMapImpl(YrsBranch wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public int len(YTransaction transaction) {
        YrsTransaction txn = transaction.getWrappedObject();

        return Yrs4J.YRS_INSTANCE.ymap_len(wrappedObject, txn);
    }

    @Override
    public void insert(YTransaction transaction, String key, YInput input) {
        YrsTransaction txn = transaction.getWrappedObject();
        YrsInput in = input.getWrappedObject();

        Yrs4J.YRS_INSTANCE.ymap_insert(wrappedObject, txn, key, in);
    }

    @Override
    public void remove(YTransaction transaction, String key) {
        YrsTransaction txn = transaction.getWrappedObject();
        Yrs4J.YRS_INSTANCE.ymap_remove(wrappedObject, txn, key);
    }

    @Override
    public YOutput get(YTransaction transaction, String key) {
        YrsTransaction txn = transaction.getWrappedObject();
        YrsOutput out = Yrs4J.YRS_INSTANCE.ymap_get(wrappedObject, txn, key);
        return YOutput.wrap(out);
    }

    @Override
    public void removeAll(YTransaction transaction) {
        YrsTransaction txn = transaction.getWrappedObject();
        Yrs4J.YRS_INSTANCE.ymap_remove_all(wrappedObject, txn);
    }

    @Override
    public YMapIter iter(YTransaction transaction) {
        YrsTransaction txn = transaction.getWrappedObject();

        YMapIter iter = YMapIter.wrap(Yrs4J.YRS_INSTANCE.ymap_iter(wrappedObject, txn));
        registerDestroyable(iter);
        return iter;
    }

    @Override
    public void setTransaction(YTransaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public Iterator<YMapEntry> iterator() {
        if (this.transaction == null) throw new RuntimeException(new IllegalStateException("Transaction is not set"));

        return new YIteratorImpl<>(iter(transaction));
    }

    @Override
    public void forEach(Consumer<? super YMapEntry> action) {
        YMap.super.forEach(action);
    }

}
