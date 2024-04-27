package at.yrs4j.wrapper;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.yrslib.YrsDoc;
import at.yrs4j.yrslib.YrsOptions;
import at.yrs4j.yrslib.YrsTransaction;

public class YDocImpl extends AbstractJNAWrapper<YrsDoc> implements YDoc {

    YDocImpl() {
        super(Yrs4J.YRS_INSTANCE.ydoc_new());
    }

    YDocImpl(YrsOptions.ByValue options) {
        super(Yrs4J.YRS_INSTANCE.ydoc_new_with_options(options));
    }

    YDocImpl(YrsDoc doc) {
        super(doc);
    }
    @Override
    public void destroy() {
        Yrs4J.YRS_INSTANCE.ydoc_destroy(super.wrappedObject);
    }

    @Override
    public YDoc clone(YDoc YDoc) {
        YrsDoc docCloned = Yrs4J.YRS_INSTANCE.ydoc_clone(((YDocImpl) YDoc).wrappedObject);
        return new YDocImpl(docCloned);
    }

    @Override
    public long id() {
        return Yrs4J.YRS_INSTANCE.ydoc_id(super.wrappedObject);
    }

    @Override
    public String guid() {
        return Yrs4J.YRS_INSTANCE.ydoc_guid(super.wrappedObject).getString(0);
    }

    @Override
    public String collectionId() {
        return Yrs4J.YRS_INSTANCE.ydoc_collection_id(super.wrappedObject).getString(0);
    }

    @Override
    public boolean shouldLoad() {
        return Yrs4J.YRS_INSTANCE.ydoc_should_load(super.wrappedObject) != 0;
    }

    @Override
    public boolean autoLoad() {
        return Yrs4J.YRS_INSTANCE.ydoc_auto_load(super.wrappedObject) != 0;
    }

    @Override
    public void load(YrsTransaction parentTransaction) {
        Yrs4J.YRS_INSTANCE.ydoc_load(super.wrappedObject, parentTransaction);
    }

    @Override
    public void clear(YrsTransaction parentTransaction) {
        Yrs4J.YRS_INSTANCE.ydoc_clear(super.wrappedObject, parentTransaction);
    }

    @Override
    public YTransaction readTransaction() {
        return new YTransactionImpl(Yrs4J.YRS_INSTANCE.ydoc_read_transaction(super.wrappedObject));
    }

    @Override
    public YTransaction writeTransaction(Integer origin_len, String origin) {
        return new YTransactionImpl(Yrs4J.YRS_INSTANCE.ydoc_write_transaction(super.wrappedObject, origin_len, origin));
    }

    @Override
    public YTransaction writeTransaction() {
        return new YTransactionImpl(Yrs4J.YRS_INSTANCE.ydoc_write_transaction(super.wrappedObject, 0, null));
    }
}
