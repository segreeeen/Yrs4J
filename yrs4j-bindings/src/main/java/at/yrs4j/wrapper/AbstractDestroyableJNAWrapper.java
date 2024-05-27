package at.yrs4j.wrapper;

public abstract class AbstractDestroyableJNAWrapper<T> extends AbstractJNAWrapper<T> implements Destroyable {

    protected boolean destroyed = false;

    public AbstractDestroyableJNAWrapper(T wrappedObject) {
        super(wrappedObject);
        registerDestroyable(this);
    }

    @Override
    public void destroy() {
        this.destroyed = true;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

}
