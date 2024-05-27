package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.wrapper.AbstractDestroyableJNAWrapper;
import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.YXmlAttr;
import at.yrs4j.wrapper.interfaces.YXmlAttrIter;
import at.yrs4j.yrslib.YrsXmlAttr;
import at.yrs4j.yrslib.YrsXmlAttrIter;
import at.yrs4j.yrslib.YrsXmlTreeWalker;

public class YXmlAttrIterImpl extends AbstractDestroyableJNAWrapper<YrsXmlAttrIter> implements YXmlAttrIter {
    public YXmlAttrIterImpl(YrsXmlAttrIter wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public void destroy() {
        Yrs4J.YRS_INSTANCE.yxmlattr_iter_destroy(wrappedObject);
        super.destroy();
    }

    @Override
    public YXmlAttr next() {
        YrsXmlAttr attrNative = Yrs4J.YRS_INSTANCE.yxmlattr_iter_next(wrappedObject);

        if (attrNative != null) {
            return YXmlAttr.wrap(attrNative);
        }

        return null;
    }
}
