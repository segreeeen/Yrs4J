package at.yrs4j.example;

import at.yrs4j.api.Yrs4J;
import at.yrs4j.utils.EncodingType;
import at.yrs4j.wrapper.*;
import at.yrs4j.libnative.linux.LinuxLibLoader;

public class Main {
    public static void main(String[] args) {
        Yrs4J.init(LinuxLibLoader.create());

        example();
        updateExchangeBasic();
        yTextBasic();
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

    static YDoc createJDocWithId(long id) {
        YOptions options = YOptions.create();
        options.setEncoding(EncodingType.Y_OFFSET_UTF16);
        options.setId(id);
        options.setSkipGc(false);

        return YDoc.createWithOptions(options);
    }

    static void updateExchangeBasic() {
        YDoc d1 = createJDocWithId(1);
        YText txt1 = YText.createFromDoc(d1, "test");
        YTransaction t1 = d1.writeTransaction();

        YDoc d2 = createJDocWithId(2);
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
        YDoc YDoc = createJDocWithId(1);
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

    }

    static void exchangeUpdates() {
        //TODO impl later...
    }
}