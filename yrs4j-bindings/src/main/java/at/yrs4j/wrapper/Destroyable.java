package at.yrs4j.wrapper;

/**
 * Destroyable Interface denoting a wrapped YRS struct that has a destroy() function which has to be called to deallocate memory
 */
public interface Destroyable {
    void destroy();

    boolean isDestroyed();
}
