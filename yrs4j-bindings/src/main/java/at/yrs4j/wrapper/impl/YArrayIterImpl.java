package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.YArrayIter;
import at.yrs4j.wrapper.interfaces.YOutput;
import at.yrs4j.yrslib.YrsArrayIter;
import at.yrs4j.yrslib.YrsOutput;

public class YArrayIterImpl extends AbstractJNAWrapper<YrsArrayIter> implements YArrayIter {

    public YArrayIterImpl(YrsArrayIter wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public void destroy() {
        Yrs4J.YRS_INSTANCE.yarray_iter_destroy(wrappedObject);
    }

    @Override
    public YOutput next() {
        YrsOutput output = Yrs4J.YRS_INSTANCE.yarray_iter_next(wrappedObject);
        if (output != null) {
            return YOutput.wrap(output);
        }

        return null;
    }

}
