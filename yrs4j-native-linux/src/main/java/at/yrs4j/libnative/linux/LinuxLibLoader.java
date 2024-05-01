package at.yrs4j.libnative.linux;

import at.yrs4j.api.LibLoader;
import at.yrs4j.api.YrsLibNativeInterface;
import com.sun.jna.Native;

import java.io.File;
import java.io.IOException;


public class LinuxLibLoader implements LibLoader {
    public static final String LIB_NAME = "libyrs.so";
    private static volatile YrsLibNativeInterface yrsInstance;

    public static LinuxLibLoader create() {
        return new LinuxLibLoader();
    }

    @Override
    public YrsLibNativeInterface get() {
        return load();
    }

    private static synchronized YrsLibNativeInterface load() {
        if (yrsInstance == null) {
            File jnaNativeLib;
            try {
                jnaNativeLib = Native.extractFromResourcePath(LIB_NAME, LinuxLibLoader.class.getClassLoader());
                yrsInstance = Native.load(jnaNativeLib.getAbsolutePath(), YrsLibNativeInterface.class);
            } catch (IOException e) {
                throw new RuntimeException("Your Operating System is not supported", e);
            }
        }
        return yrsInstance;
    }
}