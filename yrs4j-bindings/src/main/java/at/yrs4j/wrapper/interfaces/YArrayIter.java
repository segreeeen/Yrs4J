package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.impl.YArrayIterImpl;
import at.yrs4j.yrslib.YrsArrayIter;

public interface YArrayIter extends YIterable<YOutput> {

    static YArrayIter wrap(YrsArrayIter yarrayIter) {
        return new YArrayIterImpl(yarrayIter);
    }
}
