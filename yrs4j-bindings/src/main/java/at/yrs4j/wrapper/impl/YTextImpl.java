package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.utils.JNAUtils;
import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.YChunk;
import at.yrs4j.wrapper.interfaces.YDoc;
import at.yrs4j.wrapper.interfaces.YText;
import at.yrs4j.wrapper.interfaces.YTransaction;
import at.yrs4j.yrslib.YrsBranch;
import at.yrs4j.yrslib.YrsChunk;
import at.yrs4j.yrslib.YrsInput;
import at.yrs4j.yrslib.YrsTransaction;
import com.sun.jna.Pointer;

import java.nio.IntBuffer;

public class YTextImpl extends AbstractJNAWrapper<YrsBranch> implements YText {

    public YTextImpl(YrsBranch wrappedObject) {
        super(wrappedObject);
    }

    public YTextImpl(YDoc doc, String name) {
        super(Yrs4J.YRS_INSTANCE.ytext(doc.getWrappedObject(), name));
    }

    @Override
    public int len(YTransaction transaction) {
        return Yrs4J.YRS_INSTANCE.ytext_len(super.wrappedObject, transaction.getWrappedObject());
    }

    @Override
    public String string(YTransaction transaction) {
        return JNAUtils.getYrsString(Yrs4J.YRS_INSTANCE.ytext_string(super.wrappedObject, transaction.getWrappedObject()));
    }

    @Override
    public void insert(YTransaction transaction, int index, String value, YrsInput attrs) {
        Yrs4J.YRS_INSTANCE.ytext_insert(super.wrappedObject, transaction.getWrappedObject(), index, value, attrs);
    }

    @Override
    public void format(YTransaction transaction, int index, int len, YrsInput attrs) {
        Yrs4J.YRS_INSTANCE.ytext_format(super.wrappedObject, transaction.getWrappedObject(), index, len, attrs);
    }

    @Override
    public void insertEmbed(YTransaction transaction, int index, YrsInput content, YrsInput attrs) {
        Yrs4J.YRS_INSTANCE.ytext_insert_embed(super.wrappedObject, transaction.getWrappedObject(), index, content, attrs);
    }


    @Override
    public void removeRange(YTransaction transaction, int index, int length) {
        Yrs4J.YRS_INSTANCE.ytext_remove_range(super.wrappedObject, transaction.getWrappedObject(), index, length);
    }

    @Override
    public YChunk chunks(YTransaction transaction) {
        IntBuffer buffer = IntBuffer.allocate(1);
        YrsTransaction txn = transaction.getWrappedObject();

        YrsChunk chunkNative = Yrs4J.YRS_INSTANCE.ytext_chunks(wrappedObject, txn, buffer);

        YChunk chunk = YChunk.wrap(chunkNative);
        ((YChunkImpl)chunk).setLength(buffer.get(0));
        return YChunk.wrap(chunkNative);


    }
}
