package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.wrapper.AbstractDestroyableJNAWrapper;
import at.yrs4j.wrapper.interfaces.YChunk;
import at.yrs4j.wrapper.interfaces.YOutput;
import at.yrs4j.yrslib.YrsChunk;

public class YChunkImpl extends AbstractDestroyableJNAWrapper<YrsChunk> implements YChunk {

    private int length = 0;

    public YChunkImpl(YrsChunk wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public YOutput getData() {
        return YOutput.wrap(wrappedObject.data);
    }

    @Override
    public int getFmtLen() {
        return wrappedObject.fmt_len;
    }

    @Override
    public int getLength() {
        return length;
    }

    @Override
    public void destroy() {
        Yrs4J.YRS_INSTANCE.ychunks_destroy(wrappedObject, length);
    }

    void setLength(int length) {
        this.length = length;
    }
}
