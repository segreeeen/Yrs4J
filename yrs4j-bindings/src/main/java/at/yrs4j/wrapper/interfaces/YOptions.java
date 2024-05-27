package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.JNAWrapper;
import at.yrs4j.wrapper.impl.YOptionsImpl;
import at.yrs4j.yrslib.YrsOptions;
import com.sun.jna.Pointer;

public interface YOptions extends JNAWrapper<YrsOptions> {
    long getId();

    void setId(long id);

    String getGuid();

    void setGuid(String guid);

    String getCollectionId();

    void setCollectionId(String collection_id);

    byte getEncoding();

    /**
     * Encoding used by text editing operations on this document. It's used to compute
     * <br>
     * `YText`/`YXmlText` insertion offsets and text lengths. Either:
     * <br>
     * <br>
     * - `Y_OFFSET_BYTES`
     * <br>
     * - `Y_OFFSET_UTF16`
     */
    void setEncoding(EncodingType encoding);

    boolean skipGc();

    void setSkipGc(boolean skip_gc);

    boolean autoLoad();

    void setAutoLoad(boolean auto_load);

    boolean shouldLoad();

    void setShouldLoad(boolean should_load);

    // Static factory methods
    static YOptions create() {
        return new YOptionsImpl();
    }

    static YOptions wrap(YrsOptions wrappedObject) {
        return new YOptionsImpl(wrappedObject);
    }
}
