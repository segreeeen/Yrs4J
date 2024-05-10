package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.utils.JNAUtils;
import at.yrs4j.wrapper.interfaces.YDoc;
import at.yrs4j.wrapper.interfaces.YInput;
import at.yrs4j.yrslib.YrsInput;

import java.util.Arrays;

public class YInputFactory {

    public static YInput createNull() {
        YrsInput input = Yrs4J.YRS_INSTANCE.yinput_null();
        return new YInputImpl(input);
    }


    public static YInput createUndefined() {
        YrsInput input = Yrs4J.YRS_INSTANCE.yinput_undefined();
        return new YInputImpl(input);
    }


    public static YInput createBool(boolean flag) {
        YrsInput input = Yrs4J.YRS_INSTANCE.yinput_bool(flag ? (byte) 1 : (byte) 0);
        return new YInputImpl(input);
    }


    public static YInput createFloat(double num) {
        YrsInput input = Yrs4J.YRS_INSTANCE.yinput_float(num);
        return new YInputImpl(input);
    }


    public static YInput createLong(long num) {
        YrsInput input = Yrs4J.YRS_INSTANCE.yinput_long(num);
        return new YInputImpl(input);
    }


    public static YInput createString(String str) {
        YrsInput input = Yrs4J.YRS_INSTANCE.yinput_string(str);
        return new YInputImpl(input);
    }


    public static YInput createBinary(byte[] buf) {
        YrsInput input = Yrs4J.YRS_INSTANCE.yinput_binary(buf, buf.length);
        return new YInputImpl(input);
    }


    public static YInput createJsonArray(YInput[] array) {
        YrsInput[] nativeArray = JNAUtils.createYrsInputArray(array);
        YrsInput input = Yrs4J.YRS_INSTANCE.yinput_json_array(nativeArray, array.length);
        return new YInputImpl(input);
    }


    public static YInput createJsonMap(String[] keys, YInput[] value) {
        return getYInput(keys, value);
    }


    public static YInput createYArray(YInput[] array) {
        YrsInput[] nativeArray = JNAUtils.createYrsInputArray(array);

        YrsInput input = Yrs4J.YRS_INSTANCE.yinput_yarray(nativeArray, array.length);

        return new YInputImpl(input);
    }




    public static YInput createYMap(String[] keys, YInput[] value) {
        return getYInput(keys, value);
    }


    public static YInput createYText(String str) {
        YrsInput input = Yrs4J.YRS_INSTANCE.yinput_ytext(str);
        return new YInputImpl(input);
    }


    public static YInput createXmlElem(String str) {
        YrsInput input = Yrs4J.YRS_INSTANCE.yinput_yxmlelem(str);
        return new YInputImpl(input);
    }


    public static YInput createXmlText(String str) {
        YrsInput input = Yrs4J.YRS_INSTANCE.yinput_yxmltext(str);
        return new YInputImpl(input);
    }


    public static YInput createYDoc(YDoc doc) {
        YrsInput input = Yrs4J.YRS_INSTANCE.yinput_ydoc(((YDocImpl) doc).getWrappedObject());
        return new YInputImpl(input);
    }


    private static YInput getYInput(String[] keys, YInput[] value) {
        Integer length = keys.length == value.length ? keys.length : null;
        if (length == null) return null;

        YrsInput[] nativeArray = Arrays.stream(value).map(o -> ((YInputImpl) o).getWrappedObject()).toArray(YrsInput[]::new);

        YrsInput input = Yrs4J.YRS_INSTANCE.yinput_json_map(keys, nativeArray, length);

        return new YInputImpl(input);
    }
}
