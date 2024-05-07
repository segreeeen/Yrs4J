package at.yrs4j.wrapper.interfaces;

public enum ValueType {
        Y_JSON_NULL(-1),
        Y_JSON_STR(-5),
        Y_DOC(7),
        Y_JSON_UNDEF(0),
        Y_ARRAY(1),
        Y_MAP(2),
        Y_JSON_NUM(-7),
        Y_JSON_BUF(-4),
        Y_JSON_INT(-6),
        Y_JSON_BOOL(-8),
        Y_JSON_MAP(-2),
        Y_JSON_ARR(-3),
        Y_WEAK_LINK(8),
        Y_TEXT(3);

        private final int value;

    ValueType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static ValueType findByValue(int value) {
            for (ValueType type : ValueType.values()) {
                if (type.getValue() == value) {
                    return type;
                }
            }
            throw new IllegalArgumentException("No constant with value " + value + " found");
        }
    }