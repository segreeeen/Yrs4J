package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.impl.YDocImpl;
import at.yrs4j.wrapper.impl.YOptionsImpl;
import at.yrs4j.yrslib.YrsDoc;
import at.yrs4j.yrslib.YrsOptions;
import at.yrs4j.yrslib.YrsTransaction;

public interface YDoc {
    void destroy();
    YDoc clone(YDoc YDoc);
    long id();
    String guid();
    String collectionId();
    boolean shouldLoad();
    boolean autoLoad();
    void load(YrsTransaction parentTransaction);
    void clear(YrsTransaction parentTransaction);
    YTransaction readTransaction();
    YTransaction writeTransaction(Integer origin_len, String origin);
    YTransaction writeTransaction();

    // Factory method for default YDoc
    static YDoc create() {
        return new YDocImpl();
    }

    // Factory method with YOptions
    static YDoc createWithOptions(YOptions YOptions) {
        return new YDocImpl((YrsOptions.ByValue) ((YOptionsImpl) YOptions).getWrappedObject());
    }

    // Factory method with ID
    static YDoc createWithId(YOptions YOptions) {
        return new YDocImpl((YrsOptions.ByValue) ((YOptionsImpl) YOptions).getWrappedObject());
    }

    // Factory method for wrapping existing YrsDoc
    static YDoc wrap(YrsDoc doc) {
        return new YDocImpl(doc);
    }
}
