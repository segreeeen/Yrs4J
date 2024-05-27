package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.JNAWrapper;
import at.yrs4j.wrapper.impl.YXmlTreeWalkerImpl;
import at.yrs4j.yrslib.YrsXmlTreeWalker;

public interface YXmlTreeWalker extends YIterator<YOutput>, JNAWrapper<YrsXmlTreeWalker> {
    static YXmlTreeWalker wrap(YrsXmlTreeWalker walker) {
        return new YXmlTreeWalkerImpl(walker);
    }
}
