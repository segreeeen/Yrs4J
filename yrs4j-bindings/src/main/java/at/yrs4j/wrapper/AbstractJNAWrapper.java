package at.yrs4j.wrapper;

public class AbstractJNAWrapper<T> {
    protected T wrappedObject;

    public AbstractJNAWrapper(T wrappedObject) {
        this.wrappedObject = wrappedObject;
    }

    public T getWrappedObject() {
        return wrappedObject;
    }
}
