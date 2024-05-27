package at.yrs4j.wrapper.interfaces;

public interface YIterable<T> extends Iterable<T> {
    YIterator<T> iter(YTransaction transaction);
}
