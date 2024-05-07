package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.utils.JNAUtils;
import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.*;
import at.yrs4j.yrslib.YrsDoc;
import at.yrs4j.yrslib.YrsOutput;
import com.sun.jna.Pointer;

public class YOutputImpl extends AbstractJNAWrapper<YrsOutput> implements YOutput {

    public YOutputImpl(YrsOutput wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public ValueType getTagValueType() {
        return ValueType.findByValue(super.wrappedObject.tag);
    }

    @Override
    public YDoc readYDoc() {
        YrsDoc doc = Yrs4J.YRS_INSTANCE.youtput_read_ydoc(super.wrappedObject);
        return YDoc.wrap(doc);
    }

    @Override
    public Boolean readBool() {
        if (getTagValueType() == ValueType.Y_JSON_BOOL) {
            byte flag = Yrs4J.YRS_INSTANCE.youtput_read_bool(super.wrappedObject).getByte(0);

            return flag != 0;
        }

        return null;
    }

    @Override
    public Double readFloat() {
        if (getTagValueType() == ValueType.Y_JSON_NUM) {

            return Yrs4J.YRS_INSTANCE.youtput_read_float(super.wrappedObject).getValue();
        }

        return null;
    }

    @Override
    public Long readLong() {
        if (getTagValueType() == ValueType.Y_JSON_INT) {

            return Yrs4J.YRS_INSTANCE.youtput_read_long(super.wrappedObject).getValue();
        }

        return null;
    }

    @Override
    public String readString() {
        if (getTagValueType() == ValueType.Y_JSON_STR) {

            return Yrs4J.YRS_INSTANCE.youtput_read_string(super.wrappedObject).getString(0);
        }

        return null;
    }

    @Override
    public byte[] readBinary() {
        if (getTagValueType() == ValueType.Y_JSON_BUF) {
            Pointer buff = Yrs4J.YRS_INSTANCE.youtput_read_binary(super.wrappedObject);
            JNAUtils.readByteArrayFromPointer(buff, wrappedObject.len);
        }

        return null;
    }

    @Override
    public YOutput readJsonArray() {
        if (getTagValueType() == ValueType.Y_JSON_ARR) {
            return YOutput.wrap(Yrs4J.YRS_INSTANCE.youtput_read_json_array(super.wrappedObject));
        }

        return null;
    }

    @Override
    public YMapEntry readJsonMap() {
        if (getTagValueType() == ValueType.Y_JSON_MAP) {
            return YMapEntry.wrap(Yrs4J.YRS_INSTANCE.youtput_read_json_map(super.wrappedObject));
        }

        return null;
    }

    @Override
    public YMap readYMap() {
        if (getTagValueType() == ValueType.Y_MAP) {
            return YMap.wrap(Yrs4J.YRS_INSTANCE.youtput_read_ymap(super.wrappedObject));
        }

        return null;
    }

    @Override
    public YText readYText() {
        if (getTagValueType() == ValueType.Y_TEXT) {
            return YText.wrap(Yrs4J.YRS_INSTANCE.youtput_read_ytext(super.wrappedObject));
        }

        return null;
    }

    @Override
    public YWeakRef readYWeakRef() {
        return null;
    }

    @Override
    public YArray readYArray() {
        if (getTagValueType() == ValueType.Y_ARRAY) {
            return YArray.wrap(Yrs4J.YRS_INSTANCE.youtput_read_yarray(super.wrappedObject));
        }

        return null;
    }

    @Override
    public YXmlElement readYXmlElement() {
        return null;
    }

    @Override
    public YXmlText readYXmlText() {
        return null;
    }
}
