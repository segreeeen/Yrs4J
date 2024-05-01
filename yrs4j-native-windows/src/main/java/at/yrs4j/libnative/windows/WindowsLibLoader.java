package at.yrs4j.libnative.windows;

import at.yrs4j.api.LibLoader;
import at.yrs4j.api.YrsLibNativeInterface;
import com.sun.jna.Native;

import java.io.File;
import java.io.IOException;



public class WindowsLibLoader implements LibLoader {
    private static volatile YrsLibNativeInterface yrsInstance;
    public static final String LIB_NAME = "libyrs.dll";

    public static WindowsLibLoader create() {
        return new WindowsLibLoader();
    }

    @Override
    public YrsLibNativeInterface get() {
        return load();
    }

    private static synchronized YrsLibNativeInterface load() {
        if (yrsInstance == null) {
            File jnaNativeLib;
            try {
                jnaNativeLib = Native.extractFromResourcePath(LIB_NAME, WindowsLibLoader.class.getClassLoader());
                yrsInstance = Native.load(jnaNativeLib.getAbsolutePath(), YrsLibNativeInterface.class);
            } catch (IOException e) {
                throw new RuntimeException("Your Operating System is not supported", e);
            }
        }
        return yrsInstance;
    }
}