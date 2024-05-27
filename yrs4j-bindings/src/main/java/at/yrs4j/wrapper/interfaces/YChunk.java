package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.Destroyable;
import at.yrs4j.wrapper.JNAWrapper;
import at.yrs4j.wrapper.impl.YChunkImpl;
import at.yrs4j.yrslib.YrsChunk;

public interface YChunk extends Destroyable, JNAWrapper<YrsChunk> {
    static YChunk wrap(YrsChunk chunkNative) {

        return new YChunkImpl(chunkNative);
    }

    YOutput getData();

    int getFmtLen();

    int getLength();
}
