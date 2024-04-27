package at.yrs4j.wrapper;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.utils.EncodingType;
import at.yrs4j.utils.JNAUtils;
import at.yrs4j.api.Yrs4J;
import at.yrs4j.utils.EncodingType;
import at.yrs4j.utils.JNAUtils;
import at.yrs4j.yrslib.YrsOptions;
import at.yrs4j.yrslib.YrsOptions;
import com.sun.jna.Pointer;

public class YOptionsImpl extends AbstractJNAWrapper<YrsOptions> implements YOptions {


    YOptionsImpl() {
        super(Yrs4J.YRS_INSTANCE.yoptions());
    }

    YOptionsImpl(YrsOptions wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public long getId() {
        return super.wrappedObject.id;
    }

    @Override
    public void setId(long id) {
        super.wrappedObject.id = id;
        super.wrappedObject.write();  // Ensures the changes are written back to native memory
    }

    @Override
    public Pointer getGuid() {
        return super.wrappedObject.guid;
    }

    @Override
    public void setGuid(String guid) {
        super.wrappedObject.guid = JNAUtils.stringToPointer(guid);
        super.wrappedObject.write();  // Ensures the changes are written back to native memory
    }

    @Override
    public Pointer getCollectionId() {
        return super.wrappedObject.collection_id;
    }

    @Override
    public void setCollectionId(String collection_id) {
        super.wrappedObject.collection_id = JNAUtils.stringToPointer(collection_id);
        super.wrappedObject.write();  // Ensures the changes are written back to native memory
    }

    @Override
    public byte getEncoding() {
        return super.wrappedObject.encoding;
    }

    @Override
    public void setEncoding(EncodingType encoding) {
        super.wrappedObject.encoding = encoding.getValue();
        super.wrappedObject.write();  // Ensures the changes are written back to native memory
    }

    @Override
    public boolean skipGc() {
        return super.wrappedObject.skip_gc != 0;
    }

    @Override
    public void setSkipGc(boolean skip_gc) {
        super.wrappedObject.skip_gc = skip_gc ? (byte) 1 : (byte) 0;
        super.wrappedObject.write();  // Ensures the changes are written back to native memory
    }

    @Override
    public boolean autoLoad() {
        return super.wrappedObject.auto_load != 0;
    }

    @Override
    public void setAutoLoad(boolean auto_load) {
        super.wrappedObject.auto_load = auto_load ? (byte) 1 : (byte) 0;
        super.wrappedObject.write();  // Ensures the changes are written back to native memory
    }

    @Override
    public boolean shouldLoad() {
        return super.wrappedObject.should_load != 0;
    }

    @Override
    public void setShouldLoad(boolean should_load) {
        super.wrappedObject.should_load = should_load ? (byte) 1 : (byte) 0;
        super.wrappedObject.write();  // Ensures the changes are written back to native memory
    }
}
