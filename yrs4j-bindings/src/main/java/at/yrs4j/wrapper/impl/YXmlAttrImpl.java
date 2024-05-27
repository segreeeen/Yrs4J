package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.wrapper.AbstractDestroyableJNAWrapper;
import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.YXmlAttr;
import at.yrs4j.yrslib.YrsXmlAttr;

public class YXmlAttrImpl extends AbstractDestroyableJNAWrapper<YrsXmlAttr> implements YXmlAttr {
    public YXmlAttrImpl(YrsXmlAttr wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public String getName() {
        return wrappedObject.name.getString(0);
    }

    @Override
    public String getValue() {
        return wrappedObject.value.getString(0);
    }

    @Override
    public void destroy() {
        Yrs4J.YRS_INSTANCE.yxmlattr_destroy(wrappedObject);
        super.destroy();
    }
}
