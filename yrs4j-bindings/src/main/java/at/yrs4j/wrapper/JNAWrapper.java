package at.yrs4j.wrapper;

public interface JNAWrapper<T> {
    T getWrappedObject();
    void cleanup();
}
