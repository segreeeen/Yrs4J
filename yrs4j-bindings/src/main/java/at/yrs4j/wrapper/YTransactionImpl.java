package at.yrs4j.wrapper;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.api.Yrs4J;
import at.yrs4j.yrslib.*;
import at.yrs4j.yrslib.YrsDeleteSet;
import at.yrs4j.yrslib.YrsDoc;
import at.yrs4j.yrslib.YrsPendingUpdate;
import at.yrs4j.yrslib.YrsTransaction;
import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

public class YTransactionImpl extends AbstractJNAWrapper<YrsTransaction> implements YTransaction {

    YTransactionImpl(YrsTransaction wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public List<YDocImpl> subdocs() {
        IntBuffer buffer = IntBuffer.allocate(1);
        YrsDoc.ByReference[] byReferenceArray = Yrs4J.YRS_INSTANCE.ytransaction_subdocs(super.wrappedObject, buffer);
        List<YDocImpl> docList = new ArrayList<>();

        for (YrsDoc.ByReference y : byReferenceArray) {
            docList.add(new YDocImpl(y));
        }

        return docList;
    }

    @Override
    public void commit() {
        Yrs4J.YRS_INSTANCE.ytransaction_commit(super.wrappedObject);
    }

    @Override
    public boolean writeable() {
        return Yrs4J.YRS_INSTANCE.ytransaction_writeable(super.wrappedObject) != 0;
    }

    @Override
    public byte[] stateVectorV1() {
        IntByReference len = new IntByReference();
        Pointer binaryData = Yrs4J.YRS_INSTANCE.ytransaction_state_vector_v1(super.wrappedObject, len);
        return getBytes(len, binaryData);
    }

    @Override
    public byte[] stateDiffV1(byte[] stateVector) {
        IntByReference len = new IntByReference();
        Memory buffer = new Memory(stateVector.length);
        buffer.write(0, stateVector, 0, stateVector.length);

        Pointer binaryData = Yrs4J.YRS_INSTANCE.ytransaction_state_diff_v1(super.wrappedObject, buffer, stateVector.length, len);

        return getBytes(len, binaryData);
    }

    @Override
    public byte[] stateDiffV2(byte[] stateVector) {
        IntByReference len = new IntByReference();
        Memory buffer = new Memory(stateVector.length);
        buffer.write(0, stateVector, 0, stateVector.length);

        Pointer binaryData = Yrs4J.YRS_INSTANCE.ytransaction_state_diff_v2(super.wrappedObject, buffer, stateVector.length, len);

        return getBytes(len, binaryData);
    }

    @Override
    public byte[] snapshot() {
        IntByReference len = new IntByReference();
        Pointer binaryData = Yrs4J.YRS_INSTANCE.ytransaction_snapshot(super.wrappedObject, len);
        return getBytes(len, binaryData);
    }

    @Override
    public byte[] encodeStateFromSnapshotV1(byte[] stateVector) {
        IntByReference len = new IntByReference();
        Memory buffer = new Memory(stateVector.length);
        buffer.write(0, stateVector, 0, stateVector.length);

        Pointer binaryData = Yrs4J.YRS_INSTANCE.ytransaction_encode_state_from_snapshot_v1(super.wrappedObject, buffer, stateVector.length, len);

        return getBytes(len, binaryData);
    }

    @Override
    public byte[] encodeStateFromSnapshotV2(byte[] stateVector) {
        IntByReference len = new IntByReference();
        Memory buffer = new Memory(stateVector.length);
        buffer.write(0, stateVector, 0, stateVector.length);

        Pointer binaryData = Yrs4J.YRS_INSTANCE.ytransaction_encode_state_from_snapshot_v2(super.wrappedObject, buffer, stateVector.length, len);

        return getBytes(len, binaryData);
    }

    @Override
    public YrsDeleteSet pendingDs() {
        return Yrs4J.YRS_INSTANCE.ytransaction_pending_ds(super.wrappedObject);
    }

    @Override
    public YrsPendingUpdate pendingUpdate() {
        return Yrs4J.YRS_INSTANCE.ytransaction_pending_update(super.wrappedObject);
    }

    @Override
    public byte apply(byte[] diff) {
        IntByReference len = new IntByReference();
        Memory buffer = new Memory(diff.length);
        buffer.write(0, diff, 0, diff.length);

        byte binaryData = Yrs4J.YRS_INSTANCE.ytransaction_apply(super.wrappedObject, buffer, diff.length);

        return binaryData;
    }

    @Override
    public byte applyV2(byte[] diff) {
        IntByReference len = new IntByReference();
        Memory buffer = new Memory(diff.length);
        buffer.write(0, diff, 0, diff.length);

        byte binaryData = Yrs4J.YRS_INSTANCE.ytransaction_apply_v2(super.wrappedObject, buffer, diff.length);

        return binaryData;
    }

    private byte[] getBytes(IntByReference len, Pointer binaryData) {
        byte[] data;

        try {
            int length = len.getValue();
            data = new byte[length];
            binaryData.read(0, data, 0, length);
        } finally {
            Yrs4J.YRS_INSTANCE.ybinary_destroy(binaryData, len.getValue());
        }

        return data;
    }
}
