package at.yrs4j.wrapper.interfaces;

import at.yrs4j.wrapper.JNAWrapper;
import at.yrs4j.wrapper.impl.YXmlTextImpl;
import at.yrs4j.yrslib.YrsBranch;

public interface YXmlText extends YXml, JNAWrapper<YrsBranch> {
    static YXmlText wrap(YrsBranch branch) {
        return new YXmlTextImpl(branch);
    }

    int len(YTransaction transaction);
    void insert(YTransaction transaction, int index, String str, YInput attrs);
    void insertEmbed(YBranch txt, YTransaction transaction, int index, YInput content, YInput attrs);
    void format(YTransaction transaction, int index, int len, YInput attrs);

    void setTransaction(YTransaction transaction);
}
