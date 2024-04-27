package at.yrs4j.wrapper;

import at.yrs4j.yrslib.*;
import at.yrs4j.yrslib.YrsDeleteSet;
import at.yrs4j.yrslib.YrsPendingUpdate;

import java.util.List;

public interface YTransaction {
    List<YDocImpl> subdocs();
    void commit();
    boolean writeable();
    byte[] stateVectorV1();
    byte[] stateDiffV1(byte[] stateVector);
    byte[] stateDiffV2(byte[] stateVector);
    byte[] snapshot();
    byte[] encodeStateFromSnapshotV1(byte[] stateVector);
    byte[] encodeStateFromSnapshotV2(byte[] stateVector);
    YrsDeleteSet pendingDs();
    YrsPendingUpdate pendingUpdate();

    /**
    * - `ERR_CODE_IO` (**1**): couldn't read data from input stream.
            * <br>
     * - `ERR_CODE_VAR_INT` (**2**): decoded variable integer outside of the expected integer size bounds.
            * <br>
     * - `ERR_CODE_EOS` (**3**): end of stream found when more data was expected.
     * <br>
     * - `ERR_CODE_UNEXPECTED_VALUE` (**4**): decoded enum tag value was not among known cases.
            * <br>
     * - `ERR_CODE_INVALID_JSON` (**5**): failure when trying to decode JSON content.
     * <br>
     * - `ERR_CODE_OTHER` (**6**): other error type than the one specified.<br>
     */
    byte apply(byte[] diff);

    /**
     * - `ERR_CODE_IO` (**1**): couldn't read data from input stream.
     * <br>
     * - `ERR_CODE_VAR_INT` (**2**): decoded variable integer outside of the expected integer size bounds.
     * <br>
     * - `ERR_CODE_EOS` (**3**): end of stream found when more data was expected.
     * <br>
     * - `ERR_CODE_UNEXPECTED_VALUE` (**4**): decoded enum tag value was not among known cases.
     * <br>
     * - `ERR_CODE_INVALID_JSON` (**5**): failure when trying to decode JSON content.
     * <br>
     * - `ERR_CODE_OTHER` (**6**): other error type than the one specified.<br>
     */
    byte applyV2(byte[] diff);

}
