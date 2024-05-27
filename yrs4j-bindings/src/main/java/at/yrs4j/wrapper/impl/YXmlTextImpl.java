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
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;

public class YXmlTextImpl extends AbstractJNAWrapper<YrsBranch> implements YXmlText {
    private YTransaction transaction;

    public YXmlTextImpl(YrsBranch wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public Optional<YOutput> nextSibling(YTransaction transaction) {
        YrsTransaction txn = transaction.getWrappedObject();
        YrsOutput out = Yrs4J.YRS_INSTANCE.yxml_next_sibling(wrappedObject, txn);

        return out == null ? Optional.empty() : Optional.of(YOutput.wrap(out));
    }

    @Override
    public Optional<YOutput> prevSibling(YTransaction transaction) {
        YrsTransaction txn = transaction.getWrappedObject();
        YrsOutput out = Yrs4J.YRS_INSTANCE.yxml_prev_sibling(wrappedObject, txn);

        return out == null ? Optional.empty() : Optional.of(YOutput.wrap(out));
    }

    @Override
    public String string(YTransaction transaction) {
        YrsTransaction txn = transaction.getWrappedObject();
        return JNAUtils.getYrsString(Yrs4J.YRS_INSTANCE.yxmltext_string(wrappedObject, txn));
    }

    @Override
    public String getAttr(YTransaction transaction, String attrName) {
        YrsTransaction txn = transaction.getWrappedObject();
        return JNAUtils.getYrsString(Yrs4J.YRS_INSTANCE.yxmltext_get_attr(wrappedObject, txn, attrName));
    }

    @Override
    public void insertAttr(YTransaction transaction, String attrName, String attrValue) {
        Objects.requireNonNull(attrName);
        Objects.requireNonNull(attrValue);

        YrsTransaction txn = transaction.getWrappedObject();

        Yrs4J.YRS_INSTANCE.yxmltext_insert_attr(wrappedObject, txn, attrName, attrValue);
    }

    @Override
    public void removeAttr(YTransaction transaction, String attrName) {
        Objects.requireNonNull(attrName);

        YrsTransaction txn = transaction.getWrappedObject();

        Yrs4J.YRS_INSTANCE.yxmltext_remove_attr(wrappedObject, txn, attrName);
    }

    @Override
    public void removeRange(YTransaction transaction, int index, int len) {
        YrsTransaction txn = transaction.getWrappedObject();

        Yrs4J.YRS_INSTANCE.yxmltext_remove_range(wrappedObject, txn, index, len);
    }

    @Override
    public YXmlAttrIter attrIter(YTransaction transaction) {
        return YXmlAttrIter.wrap(Yrs4J.YRS_INSTANCE.yxmltext_attr_iter(wrappedObject, transaction.getWrappedObject()));
    }

    @Override
    public int len(YTransaction transaction) {
        YrsTransaction txn = transaction.getWrappedObject();
        return Yrs4J.YRS_INSTANCE.yxmltext_len(wrappedObject, txn);
    }

    @Override
    public void insert(YTransaction transaction, int index, String str, YInput attrs) {
        YrsTransaction txn = transaction.getWrappedObject();
        YrsInput attrInput = null;
        if (attrs != null) {
            attrInput = attrs.getWrappedObject();
        }

        Yrs4J.YRS_INSTANCE.yxmltext_insert(wrappedObject, txn, index, str, attrInput);
    }

    @Override
    public void insertEmbed(YBranch txt, YTransaction transaction, int index, YInput content, YInput attrs) {
        YrsTransaction txn = transaction.getWrappedObject();
        YrsInput attrInput = attrs.getWrappedObject();
        YrsInput contentInput = content.getWrappedObject();

        Yrs4J.YRS_INSTANCE.yxmltext_insert_embed(wrappedObject, txn, index, contentInput, attrInput);
    }

    @Override
    public void format(YTransaction transaction, int index, int len, YInput attrs) {
        YrsTransaction txn = transaction.getWrappedObject();
        YrsInput attrInput = attrs.getWrappedObject();

        Yrs4J.YRS_INSTANCE.yxmltext_format(wrappedObject, txn, index, len, attrInput);
    }


    @Override
    public YXmlTreeWalker iter(YTransaction transaction) {
        YrsTransaction txn = transaction.getWrappedObject();

        YXmlTreeWalker iter = YXmlTreeWalker.wrap(Yrs4J.YRS_INSTANCE.yxmlelem_tree_walker(wrappedObject, txn));
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
        YXmlText.super.forEach(action);
    }

}
