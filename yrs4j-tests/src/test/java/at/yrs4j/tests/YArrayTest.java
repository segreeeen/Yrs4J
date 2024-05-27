package at.yrs4j.tests;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.wrapper.interfaces.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YArrayTest extends TestsCommon {

    @Test
    public void yArrayBasic() {
        YDoc doc = createYDocWithId(1);
        YArray arr = YArray.createWithDocAndName(doc, "test");
        YTransaction txn = doc.writeTransaction();

        YInput[] nested = new YInput[2];
        nested[0] = YInput.createFloat(0.5);
        nested[1] = YInput.createBool(true);
        YInput nestedArray = YInput.createYArray(nested);

        YInput[] args = new YInput[3];
        args[0] = nestedArray;
        args[1] = YInput.createString("hello");
        args[2] = YInput.createLong(123);

        arr.insertRange(txn, 0, args);

        YOutput output = arr.get(txn, 0);
        ValueType type = output.getTagValueType();

        assertEquals(type, ValueType.Y_ARRAY);

        YArray output1 = output.readYArray();
        output1.len();
        YOutput output2 = output1.get(txn, 0);
        double num = output2.readFloat();

        YOutput output3 = output1.get(txn, 1);
        boolean bool = output3.readBool();

        assertEquals(0.5, num);
        assertTrue(bool);

        arr.removeRange(txn, 1, 1);

        assertEquals(2, arr.len());

        arr.setTransaction(txn);
        arr.forEach(cur -> {
            switch (cur.getTagValueType()) {
                case Y_ARRAY -> {
                    YArray a = cur.readYArray();
                    assertEquals(2, arr.len());
                }
                case Y_JSON_NUM -> {
                    assertEquals(123, cur.readLong());
                    System.out.println(cur.readLong() == 123);
                }
            }
        });
    }
}
