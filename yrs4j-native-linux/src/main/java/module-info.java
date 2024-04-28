module yrs4j.native_bin.linux {
    requires yrs4j.bindings;
    requires com.sun.jna;

    exports at.yrs4j.native_lib.linux;
}