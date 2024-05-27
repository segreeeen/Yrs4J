package at.yrs4j.wrapper.interfaces;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.wrapper.JNAWrapper;
import at.yrs4j.wrapper.impl.YDocImpl;
import at.yrs4j.wrapper.impl.YXmlElementImpl;
import at.yrs4j.yrslib.YrsBranch;
import at.yrs4j.yrslib.YrsDoc;

import java.util.Optional;

public interface YXmlElement extends YXml, JNAWrapper<YrsBranch> {
    String tag();

    Optional<YXmlElement> parent();
    int childLen(YTransaction transaction);
    Optional<YOutput> firstChild();
    YXmlTreeWalker treeWalker(YTransaction transaction);
    YXmlElement insertElem(YTransaction transaction, int index, String name);
    YXmlText insertText(YTransaction transaction, int index);
    YOutput get(YTransaction transaction, int index);
    void setTransaction(YTransaction transaction);
    static YXmlElement wrap(YrsBranch element) {
        return new YXmlElementImpl(element);
    }

    static YXmlElement createWithDocAndName(YDoc doc, String name) {
        YrsDoc docNative = doc.getWrappedObject();
        YrsBranch element = Yrs4J.YRS_INSTANCE.yxmlfragment(docNative, name);

        return new YXmlElementImpl(element);
    }
}
