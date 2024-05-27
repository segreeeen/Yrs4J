package at.yrs4j.example;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.libnative.windows.WindowsLibLoader;
import at.yrs4j.wrapper.interfaces.EncodingType;
import at.yrs4j.wrapper.interfaces.*;

public class Main {
    public static void main(String[] args) {
        Yrs4J.init(WindowsLibLoader.create());

        //example();
        //updateExchangeBasic();
        //yTextBasic();
        //yArrayBasic();
        //yMapBasic();
        yXmlElementBasic();
    }

    static void example() {
        YDoc doc = YDoc.create();
        YText txt = YText.createFromDoc(doc, "name");
        YTransaction txn = doc.writeTransaction();
        txt.insert(txn, 0, "hello world", null);

        YDoc remoteYDoc = YDoc.create();
        YText remoteTxt = YText.createFromDoc(remoteYDoc, "name");
        YTransaction remoteTxn = remoteYDoc.writeTransaction();

        byte[] remoteSv = remoteTxn.stateVectorV1();
        byte[] update = txn.stateDiffV1(remoteSv);

        txn.commit();
        doc.destroy();

        int errorCode = remoteTxn.apply(update);
        if (0 != errorCode) {
            System.exit(errorCode);
        }

        String str = remoteTxt.string(remoteTxn);

        System.out.println(str);
    }

    static YDoc createYDocWithId(long id) {
        YOptions options = YOptions.create();
        options.setEncoding(EncodingType.Y_OFFSET_UTF16);
        options.setId(id);
        options.setSkipGc(false);

        return YDoc.createWithOptions(options);
    }

    static void updateExchangeBasic() {
        YDoc d1 = createYDocWithId(1);
        YText txt1 = YText.createFromDoc(d1, "test");
        YTransaction t1 = d1.writeTransaction();

        YDoc d2 = createYDocWithId(2);
        YText txt2 = YText.createFromDoc(d2, "test");
        YTransaction t2 = d2.writeTransaction();

        txt1.insert(t1, 0, "world", null);
        txt2.insert(t2, 0, "hello", null);

        byte[] sv1 = t1.stateVectorV1();
        byte[] sv2 = t2.stateVectorV1();

        byte[] u1 = t1.stateDiffV1(sv2);
        byte[] u2 = t2.stateDiffV1(sv1);

        t1.apply(u2);
        t2.apply(u1);

        String str1 = txt1.string(t1);
        System.out.println(str1);

        String str2 = txt2.string(t2);
        System.out.println(str2);

        assert str1.equals(str2);

        t2.commit();
        t1.commit();
    }

    static void yTextBasic() {
        YDoc YDoc = createYDocWithId(1);
        YText txt = YText.createFromDoc(YDoc, "text");
        YTransaction txn = YDoc.writeTransaction();
        txt.insert(txn, 0, "hello", null);
        txt.insert(txn, 5, "world", null);
        txt.removeRange(txn,0,5);

        assert txt.len(txn) == 5;

        String str = txt.string(txn);

        System.out.println(str);

        assert str.equals("world");

        txn.commit();
    }



    static void yArrayBasic() {
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

        YArray output1 = output.readYArray();
        output1.len();
        YOutput output2 = output1.get(txn, 0);
        double num = output2.readFloat();

        YOutput output3 = output1.get(txn, 1);
        boolean bool = output3.readBool();

        System.out.println(num);
        System.out.println(bool);
        System.out.println(output1.len());

        arr.removeRange(txn, 1, 1);

        System.out.println(arr.len() == 2);
        arr.setTransaction(txn);
        arr.forEach(cur -> {
            switch (cur.getTagValueType()) {
                case Y_ARRAY -> {
                    YArray a = cur.readYArray();
                    System.out.println(a.len() == 2);
                }
                case Y_JSON_NUM -> {
                    System.out.println(cur.readLong() == 123);
                }
            }
        });

        arr.iterator();
        arr.iterator();

        arr.cleanup();

        arr.iterator();
        arr.iterator();

        txn.commit();

        Yrs4J.cleanup();

    }


    static void yMapBasic() {
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

        System.out.println(map.len(txn));

        YMapIter i = map.iter(txn);
        map.setTransaction(txn);

        map.forEach(e -> System.out.println(e.getKey()));
        YMapEntry cur = i.next();
    }

    static void yXmlElementBasic() {
        YDoc doc = createYDocWithId(1);
        YXmlElement frag = YXmlElement.createWithDocAndName(doc, "test");
        YTransaction txn = doc.writeTransaction();
        YXmlElement xml = frag.insertElem(txn, 0, "div");

        xml.insertAttr(txn, "key1", "value1");
        xml.insertAttr(txn, "key2", "value2");
        xml.insertAttr(txn, "key3", "value3");
        xml.insertAttr(txn, "key4", "value4");

        String a = xml.getAttr(txn, "key1");
        String b = xml.getAttr(txn, "key2");
        String c = xml.getAttr(txn, "key3");
        String d = xml.getAttr(txn, "key4");

        YXmlAttrIter i = xml.attrIter(txn);

        YXmlAttr attr = i.next();
        while (attr != null){
            System.out.println("Name: " + attr.getName() + ", Value: " + attr.getValue());
            attr = i.next();

        }

        YXmlElement inner = xml.insertElem(txn, 0, "p");
        YXmlText innerTxt = inner.insertText(txn, 0);
        innerTxt.insert(txn, 0, "hello", null);

        int len = xml.childLen(txn);
        System.out.println(len + " " + (len == 1));



    }

    static void exchangeUpdates() {
        //TODO impl later...
    }
}