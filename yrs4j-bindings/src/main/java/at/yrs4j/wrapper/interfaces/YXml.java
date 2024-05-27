package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.JNAWrapper;

import java.util.Optional;

public interface YXml extends YIterable<YOutput> {
    Optional<YOutput> nextSibling(YTransaction transaction);
    Optional<YOutput> prevSibling(YTransaction transaction);
    String string(YTransaction transaction);
    String getAttr(YTransaction transaction, String attrName);
    void insertAttr(YTransaction transaction, String attrName, String attrValue);
    void removeAttr(YTransaction transaction, String attrName);
    void removeRange(YTransaction transaction, int index, int len);

    YXmlAttrIter attrIter(YTransaction transaction);
}
