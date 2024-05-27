package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.Destroyable;

public interface YIterator<T> extends Destroyable {
    T next();
}
