package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.YPendingUpdate;
import at.yrs4j.yrslib.YrsPendingUpdate;
import at.yrs4j.yrslib.YrsStateVector;

public class YPendingUpdateImpl extends AbstractJNAWrapper<YrsPendingUpdate> implements YPendingUpdate {
    public YPendingUpdateImpl(YrsPendingUpdate wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public void destroy() {
        Yrs4J.YRS_INSTANCE.ypending_update_destroy(super.wrappedObject);
    }

    @Override
    public YrsStateVector getMissing() {
        return super.wrappedObject.missing;
    }

    @Override
    public byte[] getUpdateV1() {
        int length = super.getWrappedObject().update_len;
        byte[] data = new byte[length];
        super.getWrappedObject().update_v1.read(0, data, 0, length);
        return data;
    }

    @Override
    public int getUpdateLen() {
        return super.getWrappedObject().update_len;
    }
}
