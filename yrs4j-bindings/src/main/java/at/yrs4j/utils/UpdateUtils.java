package at.yrs4j.utils;

import at.yrs4j.api.Yrs4J;
import com.sun.jna.Memory;
import com.sun.jna.ptr.IntByReference;

public class UpdateUtils {
    public static String updateDebugV1(byte[] update) {
        IntByReference len = new IntByReference();
        Memory buffer = new Memory(update.length);
        buffer.write(0, update, 0, update.length);

        return Yrs4J.YRS_INSTANCE.yupdate_debug_v1(buffer, update.length).getString(0);

    }

    public static String updateDebugV2(byte[] update) {
        IntByReference len = new IntByReference();
        Memory buffer = new Memory(update.length);
        buffer.write(0, update, 0, update.length);

        return Yrs4J.YRS_INSTANCE.yupdate_debug_v2(buffer, update.length).getString(0);

    }
}
