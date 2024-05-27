package at.yrs4j.wrapper;

import at.yrs4j.api.Yrs4J;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AbstractJNAWrapper<T> implements JNAWrapper<T> {
    protected final T wrappedObject;
    private final List<Destroyable> destroyables = new ArrayList<>(100);

    public AbstractJNAWrapper(T wrappedObject) {
        this.wrappedObject = wrappedObject;
    }

    public T getWrappedObject() {
        return wrappedObject;
    }

    @Override
    public void cleanup() {
        destroyables.stream().filter(d -> !d.isDestroyed()).forEach(Destroyable::destroy);
        destroyables.clear();
    }

    protected void registerDestroyable(Destroyable destroyable) {
        Objects.requireNonNull(destroyable);
        this.destroyables.add(destroyable);
        Yrs4J.registerDestroyable(destroyable);
    }


}
