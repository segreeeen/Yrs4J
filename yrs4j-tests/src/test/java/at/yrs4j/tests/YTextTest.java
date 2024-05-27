package at.yrs4j.tests;

import at.yrs4j.wrapper.interfaces.YDoc;
import at.yrs4j.wrapper.interfaces.YText;
import at.yrs4j.wrapper.interfaces.YTransaction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class YTextTest extends TestsCommon {

    @Test
    public void yTextBasic() {
        YDoc YDoc = createYDocWithId(1);
        YText txt = YText.createFromDoc(YDoc, "text");
        YTransaction txn = YDoc.writeTransaction();
        txt.insert(txn, 0, "hello", null);
        txt.insert(txn, 5, "world", null);
        txt.removeRange(txn,0,5);
        assertEquals(5, txt.len(txn));

        String str = txt.string(txn);

        assertEquals("world", str);

        txn.commit();
    }

}
