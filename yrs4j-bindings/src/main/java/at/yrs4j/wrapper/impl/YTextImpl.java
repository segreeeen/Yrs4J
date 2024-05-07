package at.yrs4j.wrapper.impl;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.wrapper.AbstractJNAWrapper;
import at.yrs4j.wrapper.interfaces.YDoc;
import at.yrs4j.wrapper.interfaces.YText;
import at.yrs4j.wrapper.interfaces.YTransaction;
import at.yrs4j.yrslib.YrsBranch;
import at.yrs4j.yrslib.YrsInput;
import com.sun.jna.Pointer;

public class YTextImpl extends AbstractJNAWrapper<YrsBranch> implements YText {

    public YTextImpl(YrsBranch wrappedObject) {
        super(wrappedObject);
    }

    public YTextImpl(YDoc YDoc, String name) {
        super(Yrs4J.YRS_INSTANCE.ytext(((YDocImpl) YDoc).getWrappedObject(), name));
    }

    @Override
    public int len(YTransaction YTransaction) {
        return Yrs4J.YRS_INSTANCE.ytext_len(super.wrappedObject, ((YTransactionImpl) YTransaction).getWrappedObject());
    }

    @Override
    public String string(YTransaction YTransaction) {
        Pointer p = Yrs4J.YRS_INSTANCE.ytext_string(super.wrappedObject, ((YTransactionImpl) YTransaction).getWrappedObject());
        String s = p.getString(0);
        Yrs4J.YRS_INSTANCE.ystring_destroy(p);
        return s;
    }

    @Override
    public void insert(YTransaction YTransaction, int index, String value, YrsInput attrs) {
        Yrs4J.YRS_INSTANCE.ytext_insert(super.wrappedObject, ((YTransactionImpl) YTransaction).getWrappedObject(), index, value, attrs);
    }

    @Override
    public void format(YTransaction YTransaction, int index, int len, YrsInput attrs) {
        Yrs4J.YRS_INSTANCE.ytext_format(super.wrappedObject, ((YTransactionImpl) YTransaction).getWrappedObject(), index, len, attrs);
    }

    @Override
    public void insertEmbed(YTransaction YTransaction, int index, YrsInput content, YrsInput attrs) {
        Yrs4J.YRS_INSTANCE.ytext_insert_embed(super.wrappedObject, ((YTransactionImpl) YTransaction).getWrappedObject(), index, content, attrs);
    }


    @Override
    public void removeRange(YTransaction YTransaction, int index, int length) {
        Yrs4J.YRS_INSTANCE.ytext_remove_range(super.wrappedObject, ((YTransactionImpl) YTransaction).getWrappedObject(), index, length);
    }
}
