package at.yrs4j.yrslib;

import com.sun.jna.Structure;

import java.util.Arrays;
import java.util.List;

/**
 * <i>native declaration : libyrs.h:376</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class YrsInput extends Structure {
    /**
     * Tag describing, which `value` type is being stored by this input cell. Can be one of:
     * <br>
     * <br>
     * - [Y_JSON_BOOL] for boolean flags.
     * <br>
     * - [Y_JSON_NUM] for 64-bit floating point numbers.
     * <br>
     * - [Y_JSON_INT] for 64-bit signed integers.
     * <br>
     * - [Y_JSON_STR] for null-terminated UTF-8 encoded strings.
     * <br>
     * - [Y_JSON_BUF] for embedded binary data.
     * <br>
     * - [Y_JSON_ARR] for arrays of JSON-like values.
     * <br>
     * - [Y_JSON_MAP] for JSON-like objects build from key-value pairs.
     * <br>
     * - [Y_JSON_NULL] for JSON-like null values.
     * <br>
     * - [Y_JSON_UNDEF] for JSON-like undefined values.
     * <br>
     * - [Y_ARRAY] for cells which contents should be used to initialize a `YArray` shared type.
     * <br>
     * - [Y_MAP] for cells which contents should be used to initialize a `YMap` shared type.
     * <br>
     * - [Y_DOC] for cells which contents should be used to nest a `YrsDoc` sub-document.
     * <br>
     * - [Y_WEAK_LINK] for cells which contents should be used to nest a `YWeakLink` sub-document.
     */
    public byte tag;
    /**
     * Length of the contents stored by current `YrsInput` cell.
     * <br>
     * <br>
     * For [Y_JSON_NULL] and [Y_JSON_UNDEF] its equal to `0`.
     * <br>
     * <br>
     * For [Y_JSON_ARR], [Y_JSON_MAP], [Y_ARRAY] and [Y_MAP] it describes a number of passed
     * <br>
     * elements.
     * <br>
     * <br>
     * For other types it's always equal to `1`.
     */
    public int len;
    /**
     * Union struct which contains a content corresponding to a provided `tag` field.<br>
     * C type : YrsInputContent
     */
    public YrsInputContent value;

    public YrsInput() {
        super();
    }

    protected List<String> getFieldOrder() {
        return Arrays.asList("tag", "len", "value");
    }

    /**
     * @param tag   Tag describing, which `value` type is being stored by this input cell. Can be one of:
     *              <br>
     *              <br>
     *              - [Y_JSON_BOOL] for boolean flags.
     *              <br>
     *              - [Y_JSON_NUM] for 64-bit floating point numbers.
     *              <br>
     *              - [Y_JSON_INT] for 64-bit signed integers.
     *              <br>
     *              - [Y_JSON_STR] for null-terminated UTF-8 encoded strings.
     *              <br>
     *              - [Y_JSON_BUF] for embedded binary data.
     *              <br>
     *              - [Y_JSON_ARR] for arrays of JSON-like values.
     *              <br>
     *              - [Y_JSON_MAP] for JSON-like objects build from key-value pairs.
     *              <br>
     *              - [Y_JSON_NULL] for JSON-like null values.
     *              <br>
     *              - [Y_JSON_UNDEF] for JSON-like undefined values.
     *              <br>
     *              - [Y_ARRAY] for cells which contents should be used to initialize a `YArray` shared type.
     *              <br>
     *              - [Y_MAP] for cells which contents should be used to initialize a `YMap` shared type.
     *              <br>
     *              - [Y_DOC] for cells which contents should be used to nest a `YrsDoc` sub-document.
     *              <br>
     *              - [Y_WEAK_LINK] for cells which contents should be used to nest a `YWeakLink` sub-document.<br>
     * @param len   Length of the contents stored by current `YrsInput` cell.
     *              <br>
     *              <br>
     *              For [Y_JSON_NULL] and [Y_JSON_UNDEF] its equal to `0`.
     *              <br>
     *              <br>
     *              For [Y_JSON_ARR], [Y_JSON_MAP], [Y_ARRAY] and [Y_MAP] it describes a number of passed
     *              <br>
     *              elements.
     *              <br>
     *              <br>
     *              For other types it's always equal to `1`.<br>
     * @param value Union struct which contains a content corresponding to a provided `tag` field.<br>
     *              C type : YrsInputContent
     */
    public YrsInput(byte tag, int len, YrsInputContent value) {
        super();
        this.tag = tag;
        this.len = len;
        this.value = value;
    }

    public static class ByReference extends YrsInput implements Structure.ByReference {

    }

    public static class ByValue extends YrsInput implements Structure.ByValue {

    }

}
