package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.impl.YInputFactory;

public interface YInput {

    ValueType getTagValueType();

    static YInput createNull() {
        return YInputFactory.createNull();
    }

    static YInput createUndefined() {
        return YInputFactory.createUndefined();
    }
    static YInput createBool(boolean flag) {
        return YInputFactory.createBool(flag);
    }
    static YInput createFloat(double num) {
        return YInputFactory.createFloat(num);
    }
    static YInput createLong(long integer) {
        return YInputFactory.createLong(integer);
    }
    static YInput createString(String str) {
        return YInputFactory.createString(str);
    }
    static YInput createBinary(byte[] buf) {
        return YInputFactory.createBinary(buf);
    }
    static YInput createJsonArray(YInput[] array) {
        return YInputFactory.createJsonArray(array);
    }
    static YInput createJsonMap(String[] keys, YInput[] value) {
        return YInputFactory.createJsonMap(keys, value);
    }
    static YInput createYArray(YInput[] array) {
        return YInputFactory.createYArray(array);
    }
    static YInput createYMap(String[] keys, YInput[] value) {
        return YInputFactory.createYMap(keys, value);
    }
    static YInput createYText(String str) {
        return YInputFactory.createYText(str);
    }
    static YInput createXmlElem(String str) {
        return YInputFactory.createXmlElem(str);
    }
    static YInput xmlText(String str) {
        return YInputFactory.createXmlText(str);
    }
    static YInput createYDoc(YDoc doc) {
        return YInputFactory.createYDoc(doc);
    }

    int length();
}
