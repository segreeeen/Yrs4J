package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.JNAWrapper;
import at.yrs4j.wrapper.impl.YXmlAttrIterImpl;
import at.yrs4j.yrslib.YrsXmlAttrIter;

public interface YXmlAttrIter extends YIterator<YXmlAttr>, JNAWrapper<YrsXmlAttrIter> {
    static YXmlAttrIter wrap(YrsXmlAttrIter iter) {
        return new YXmlAttrIterImpl(iter);
    }
}
