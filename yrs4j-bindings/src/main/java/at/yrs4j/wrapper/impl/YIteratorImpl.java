package at.yrs4j.wrapper.impl;

import at.yrs4j.wrapper.interfaces.YIterator;

import java.util.Iterator;

class YIteratorImpl<T> implements Iterator<T> {

        final YIterator<T> iter;
        T entry;

        public YIteratorImpl(YIterator<T> iter) {
            this.iter = iter;
            entry = iter.next();
        }

        @Override
        public boolean hasNext() {
            return entry != null;
        }

        @Override
        public T next() {
            T current = entry;
            entry = iter.next();
            if (entry == null) {
                // an iterator may be destroyed automatically if iterated through till the end
                // othwerwise it will be cleaned with the parent object when cleanup() is called or Yrs4J.cleanup() is called
                iter.destroy();
            }
            return current == null ? entry : current;
        }
    }