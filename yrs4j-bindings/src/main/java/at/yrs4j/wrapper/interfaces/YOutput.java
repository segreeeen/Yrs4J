package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.impl.YOutputImpl;
import at.yrs4j.yrslib.YrsOutput;

public interface YOutput {
    static YOutput wrap(YrsOutput output) {
        return new YOutputImpl(output);
    }

    ValueType getTagValueType();
    YDoc readYDoc();
    Boolean readBool();
    Double readFloat();
    Long readLong();
    String readString();
    byte[] readBinary();
    YOutput readJsonArray();
    YMapEntry readJsonMap();
    YXmlElement readYXmlElement();
    YMap readYMap();
    YText readYText();
    YXmlText readYXmlText();
    YWeakRef readYWeakRef();
    YArray readYArray();

}
