package at.yrs4j.api;

import at.yrs4j.wrapper.Destroyable;

import java.util.ArrayList;
import java.util.List;

public class Yrs4J {
    public static YrsLibNativeInterface YRS_INSTANCE;
    private final YrsLibNativeInterface yrsInstance;

    private final static List<Destroyable> destroyables = new ArrayList<>(512);


    private Yrs4J(YrsLibNativeInterface yrsInstance) {
        this.yrsInstance = yrsInstance;
    }

    public static void init(LibLoader loader) {
        Yrs4J INSTANCE = new Yrs4J(loader.get());
        YRS_INSTANCE = INSTANCE.yrsInstance;
    }

    /**
     * Add a Destroyable object to the destoyable cache.
     *
     * @param d
     */
    public static void registerDestroyable(Destroyable d) {
        destroyables.add(d);
    }

    /**
     * Destroy all destroyable objects - only call this on program exit or when resetting all data.
     * This method frees all memory allocated by the yrs library
     */
    public static void cleanup() {
        destroyables.stream().filter(d -> !d.isDestroyed()).forEach(Destroyable::destroy);
        destroyables.clear();
    }
}
