package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.utils.JNAUtils;
import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.*;
import at.yrs4j.yrslib.YrsBranch;
import at.yrs4j.yrslib.YrsInput;
import at.yrs4j.yrslib.YrsOutput;
import at.yrs4j.yrslib.YrsTransaction;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

public class YArrayImpl extends AbstractJNAWrapper<YrsBranch> implements YArray {
    private YTransaction transaction;

    public YArrayImpl(YrsBranch wrappedObject) {
        super(wrappedObject);
    }

    public YArrayImpl(YDoc doc, String name) {
        super(getYrsBranch((YDocImpl) doc, name));
    }

    private static YrsBranch getYrsBranch(YDocImpl doc, String name) {
        Objects.requireNonNull(doc);
        Objects.requireNonNull(name);
        return Yrs4J.YRS_INSTANCE.yarray(doc.getWrappedObject(), name);
    }

    @Override
    public int len() {
        return Yrs4J.YRS_INSTANCE.yarray_len(wrappedObject);
    }

    @Override
    public YOutput get(YTransaction transaction, int index) {
        YrsTransaction txn = transaction.getWrappedObject();

        YrsOutput output = Yrs4J.YRS_INSTANCE.yarray_get(wrappedObject, txn, index);
        return YOutput.wrap(output);
    }

    @Override
    public void insertRange(YTransaction transaction, int index, YInput[] args) {
        YrsTransaction txn = transaction.getWrappedObject();
        YrsInput[] nativeArray = JNAUtils.createYrsInputArray(args);

        Yrs4J.YRS_INSTANCE.yarray_insert_range(wrappedObject, txn, index, nativeArray, nativeArray.length);
    }

    @Override
    public void removeRange(YTransaction transaction, int index, int len) {
        YrsTransaction txn = transaction.getWrappedObject();

        Yrs4J.YRS_INSTANCE.yarray_remove_range(wrappedObject, txn, index, len);
    }

    @Override
    public void move(YTransaction transaction, int source, int target) {
        YrsTransaction txn = transaction.getWrappedObject();

        Yrs4J.YRS_INSTANCE.yarray_move(wrappedObject, txn, source, target);
    }

    @Override
    public YArrayIter iter(YTransaction transaction) {
        YrsTransaction txn = transaction.getWrappedObject();

        YArrayIter iter = YArrayIter.wrap(Yrs4J.YRS_INSTANCE.yarray_iter(wrappedObject, txn));
        registerDestroyable(iter);
        return iter;
    }

    @Override
    public void setTransaction(YTransaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public Iterator<YOutput> iterator() {
        if (this.transaction == null) throw new RuntimeException(new IllegalStateException("Transaction is not set"));

        return new YIteratorImpl<>(iter(transaction));
    }

    @Override
    public void forEach(Consumer<? super YOutput> action) {
        YArray.super.forEach(action);
    }

}
