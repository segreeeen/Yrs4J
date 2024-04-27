package at.yrs4j.wrapper;

import at.yrs4j.yrslib.YrsBranch;
import at.yrs4j.yrslib.YrsInput;
import at.yrs4j.yrslib.YrsBranch;
import at.yrs4j.yrslib.YrsInput;

public interface YText extends YBranch {
    int len(YTransaction YTransaction);

    String string(YTransaction YTransaction);

    void insert(YTransaction YTransaction, int index, String value, YrsInput attrs);

    void format(YTransaction YTransaction, int index, int len, YrsInput attrs);

    void insertEmbed(YTransaction YTransaction, int index, YrsInput content, YrsInput attrs);

    void removeRange(YTransaction YTransaction, int index, int length);

    // Interface methods here (as required)

    static YText createFromBranch(YrsBranch wrappedObject) {
        return new YTextImpl(wrappedObject);
    }

    static YText createFromDoc(YDoc YDoc, String name) {
        return new YTextImpl(YDoc, name);
    }

}
