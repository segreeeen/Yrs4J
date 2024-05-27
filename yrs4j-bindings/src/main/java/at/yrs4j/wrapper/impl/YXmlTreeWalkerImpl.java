package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.wrapper.AbstractDestroyableJNAWrapper;
import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.YOutput;
import at.yrs4j.wrapper.interfaces.YXmlElement;
import at.yrs4j.wrapper.interfaces.YXmlTreeWalker;
import at.yrs4j.yrslib.YrsOutput;
import at.yrs4j.yrslib.YrsXmlTreeWalker;

public class YXmlTreeWalkerImpl extends AbstractDestroyableJNAWrapper<YrsXmlTreeWalker> implements YXmlTreeWalker {

    public YXmlTreeWalkerImpl(YrsXmlTreeWalker wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public void destroy() {
        Yrs4J.YRS_INSTANCE.yxmlelem_tree_walker_destroy(wrappedObject);
        super.destroy();
    }

    @Override
    public YOutput next() {
        YrsOutput o = Yrs4J.YRS_INSTANCE.yxmlelem_tree_walker_next(wrappedObject);

        if (o != null) {
            return YOutput.wrap(o);
        }

        return null;
    }
}
