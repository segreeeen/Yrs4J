package at.yrs4j.api;

public class Yrs4J {
    public static YrsLibNativeInterface YRS_INSTANCE;

    private final YrsLibNativeInterface yrsInstance;

    private Yrs4J(YrsLibNativeInterface yrsInstance) {
        this.yrsInstance = yrsInstance;
    }

    public static void init(LibLoader loader) {
        Yrs4J INSTANCE = new Yrs4J(loader.get());
        YRS_INSTANCE = INSTANCE.yrsInstance;
    }
}
