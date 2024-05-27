package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.Destroyable;
import at.yrs4j.wrapper.JNAWrapper;
import at.yrs4j.wrapper.impl.YXmlAttrImpl;
import at.yrs4j.yrslib.YrsXmlAttr;

public interface YXmlAttr extends JNAWrapper<YrsXmlAttr>, Destroyable {

    String getName();

    String getValue();

    static YXmlAttr wrap(YrsXmlAttr attr) {
        return new YXmlAttrImpl(attr);
    }
}
