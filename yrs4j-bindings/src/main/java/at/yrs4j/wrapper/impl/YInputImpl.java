package at.yrs4j.wrapper.impl;

import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.ValueType;
import at.yrs4j.wrapper.interfaces.YInput;
import at.yrs4j.yrslib.YrsInput;

public class YInputImpl extends AbstractJNAWrapper<YrsInput> implements YInput {
    YInputImpl(YrsInput input) {
        super(input);
    }

    @Override
    public ValueType getTagValueType() {
        return ValueType.findByValue(super.wrappedObject.tag);
    }


    @Override
    public int length() {
        return wrappedObject.len;
    }

}
