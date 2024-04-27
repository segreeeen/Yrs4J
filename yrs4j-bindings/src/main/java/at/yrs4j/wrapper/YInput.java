package at.yrs4j.wrapper;

public interface YInput {
    YInput createNull();
    YInput createUndefined();
    YInput createBool(boolean flag);
    YInput createFloat(double num);
    YInput createLong(long integer);
    YInput createString(String str);
    YInput createBinary(byte[] buf);
    YInput createJsonArray();
    YInput createJsonMap();
    YInput createYArray();
    YInput createYMap();

}
