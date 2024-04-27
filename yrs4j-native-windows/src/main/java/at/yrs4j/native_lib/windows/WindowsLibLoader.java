package at.yrs4j.native_lib.windows;

import at.yrs4j.api.LibLoader;
import at.yrs4j.api.YrsLibNativeInterface;
import com.sun.jna.Native;

import java.io.File;
import java.io.IOException;



public class WindowsLibLoader implements LibLoader {
    private static final YrsLibNativeInterface yrsInstance;

    static {
        File jnaNativeLib = null;
        try {
            jnaNativeLib = Native.extractFromResourcePath("win32-x86-64/at/yrs4j/native_lib/windows/yrs.dll", WindowsLibLoader.class.getClassLoader());
            yrsInstance = Native.load(jnaNativeLib.getAbsolutePath(), YrsLibNativeInterface.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static WindowsLibLoader create() {
        return new WindowsLibLoader();
    }

    @Override
    public YrsLibNativeInterface get() {
        return yrsInstance;
    }
}