package at.yrs4j.tests;

import at.yrs4j.wrapper.interfaces.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class YMapTest extends TestsCommon {

    @Test
    public void yMapBasic() {
        YDoc doc = YDoc.create();
        YMap map = YMap.createWithDocAndName(doc, "test");
        YTransaction txn = doc.writeTransaction();

        YInput a = YInput.createString("value");
        map.insert(txn, "a", a);

        YInput[] array = new YInput[2];
        array[0] = YInput.createLong(11);
        array[1] = YInput.createLong(22);
        YInput b = YInput.createJsonArray(array);

        map.insert(txn, "b", b);


        assertEquals(2, map.len(txn));

        map.setTransaction(txn);

        YMapIter i = map.iter(txn);

        AtomicInteger iterCount = new AtomicInteger();

        for (YMapEntry e: map) {
            System.out.println("fu");
        }

        map.forEach(e -> iterCount.getAndIncrement());

        assertEquals(2, iterCount.get());

    }
}
