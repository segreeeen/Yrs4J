package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.utils.JNAUtils;
import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.*;
import at.yrs4j.yrslib.YrsBranch;
import at.yrs4j.yrslib.YrsOutput;
import at.yrs4j.yrslib.YrsTransaction;

import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;

public class YXmlElementImpl extends AbstractJNAWrapper<YrsBranch> implements YXmlElement {
    private YTransaction transaction;

    public YXmlElementImpl(YrsBranch wrappedObject) {
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
        return JNAUtils.getYrsString(Yrs4J.YRS_INSTANCE.yxmlelem_string(wrappedObject, txn));
    }

    @Override
    public String getAttr(YTransaction transaction, String attrName) {
        YrsTransaction txn = transaction.getWrappedObject();
        return JNAUtils.getYrsString(Yrs4J.YRS_INSTANCE.yxmlelem_get_attr(wrappedObject, txn, attrName));
    }

    @Override
    public void insertAttr(YTransaction transaction, String attrName, String attrValue) {
        Objects.requireNonNull(attrName);
        Objects.requireNonNull(attrValue);

        YrsTransaction txn = transaction.getWrappedObject();

        Yrs4J.YRS_INSTANCE.yxmlelem_insert_attr(wrappedObject, txn, attrName, attrValue);
    }

    @Override
    public void removeAttr(YTransaction transaction, String attrName) {
        Objects.requireNonNull(attrName);

        YrsTransaction txn = transaction.getWrappedObject();

        Yrs4J.YRS_INSTANCE.yxmlelem_remove_attr(wrappedObject, txn, attrName);
    }

    @Override
    public void removeRange(YTransaction transaction, int index, int len) {
        YrsTransaction txn = transaction.getWrappedObject();

        Yrs4J.YRS_INSTANCE.yxmlelem_remove_range(wrappedObject, txn, index, len);
    }

    @Override
    public YXmlAttrIter attrIter(YTransaction transaction) {
        return YXmlAttrIter.wrap(Yrs4J.YRS_INSTANCE.yxmlelem_attr_iter(wrappedObject, transaction.getWrappedObject()));
    }

    @Override
    public String tag() {
        return Yrs4J.YRS_INSTANCE.yxmlelem_tag(wrappedObject).getString(0);
    }

    @Override
    public Optional<YXmlElement> parent() {
        YrsBranch elem = Yrs4J.YRS_INSTANCE.yxmlelem_parent(wrappedObject);
        if (elem != null) {
            return Optional.of(YXmlElement.wrap(elem));
        }

        return Optional.empty();
    }

    @Override
    public int childLen(YTransaction transaction) {
        YrsTransaction txn = transaction.getWrappedObject();

        return Yrs4J.YRS_INSTANCE.yxmlelem_child_len(wrappedObject, txn);
    }

    @Override
    public Optional<YOutput> firstChild() {
        YrsOutput out = Yrs4J.YRS_INSTANCE.yxmlelem_first_child(wrappedObject);
        return out == null ? Optional.empty() : Optional.of(YOutput.wrap(out));
    }

    @Override
    public YXmlTreeWalker treeWalker(YTransaction transaction) {
        YrsTransaction txn = transaction.getWrappedObject();

        return YXmlTreeWalker.wrap(Yrs4J.YRS_INSTANCE.yxmlelem_tree_walker(wrappedObject, txn));
    }

    @Override
    public YXmlElement insertElem(YTransaction transaction, int index, String name) {
        if (this.childLen(transaction) < index)
            throw new IndexOutOfBoundsException(index + "is exceeding the size of xmlelement");

        YrsTransaction txn = transaction.getWrappedObject();

        return YXmlElement.wrap(Yrs4J.YRS_INSTANCE.yxmlelem_insert_elem(wrappedObject, txn, index, name));
    }

    @Override
    public YXmlText insertText(YTransaction transaction, int index) {
        if (this.childLen(transaction) < index)
            throw new IndexOutOfBoundsException(index + "is exceeding the size of xmlelement");

        YrsTransaction txn = transaction.getWrappedObject();

        return YXmlText.wrap(Yrs4J.YRS_INSTANCE.yxmlelem_insert_text(wrappedObject, txn, index));
    }

    @Override
    public YOutput get(YTransaction transaction, int index) {
        if (this.childLen(transaction) < index)
            throw new IndexOutOfBoundsException(index + "is exceeding the size of xmlelement");

        YrsTransaction txn = transaction.getWrappedObject();

        return YOutput.wrap(Yrs4J.YRS_INSTANCE.yxmlelem_get(wrappedObject, txn, index));
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
        YXmlElement.super.forEach(action);
    }

}
