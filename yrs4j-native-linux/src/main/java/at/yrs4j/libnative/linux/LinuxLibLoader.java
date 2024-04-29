package at.yrs4j.libnative.linux;

import at.yrs4j.api.LibLoader;
import at.yrs4j.api.YrsLibNativeInterface;
import com.sun.jna.Native;

import java.io.File;
import java.io.IOException;


public class LinuxLibLoader implements LibLoader {
    private static final YrsLibNativeInterface yrsInstance;

    static {
        File jnaNativeLib = null;
        try {
            jnaNativeLib = Native.extractFromResourcePath("libyrs.so", LinuxLibLoader.class.getClassLoader());
            yrsInstance = Native.load(jnaNativeLib.getAbsolutePath(), YrsLibNativeInterface.class);
        } catch (IOException e) {
            throw new RuntimeException("Your Operating System is not supported", e);
        }
    }

    public static LinuxLibLoader create() {
        return new LinuxLibLoader();
    }

    @Override
    public YrsLibNativeInterface get() {
        return yrsInstance;
    }
}