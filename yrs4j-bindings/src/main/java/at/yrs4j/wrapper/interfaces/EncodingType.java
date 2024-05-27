package at.yrs4j.wrapper.interfaces;

public enum EncodingType {
    Y_OFFSET_BYTES((byte)0),
    Y_OFFSET_UTF16((byte)1);

    private final byte value;

    EncodingType(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return this.value;
    }

    public static EncodingType fromByte(byte value) {
        for (EncodingType type : EncodingType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown encoding type value: " + value);
    }
}
