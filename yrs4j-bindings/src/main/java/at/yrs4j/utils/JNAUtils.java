package at.yrs4j.utils;

import at.yrs4j.wrapper.impl.YInputImpl;
import at.yrs4j.wrapper.interfaces.YInput;
import at.yrs4j.yrslib.YrsInput;
import com.sun.jna.Memory;
import com.sun.jna.Pointer;

import java.nio.charset.StandardCharsets;

public class JNAUtils {
    public static Pointer stringToPointer(String s) {
        byte[] bytes = s.getBytes(StandardCharsets.UTF_8);  // Convert string to bytes
        Memory memory = new Memory(bytes.length + 1);       // Allocate memory (plus one for the null terminator)
        memory.write(0, bytes, 0, bytes.length);            // Write bytes to memory
        memory.setByte(bytes.length, (byte) 0);             // Null-terminate the C string
        return memory;                                      // Return the pointer to the memory
    }

    public static Memory memoryFromByteArray(byte[] arr) {
        Memory buffer = new Memory(arr.length);
        buffer.write(0, arr, 0, arr.length);
        return buffer;
    }

    public static byte[] readByteArrayFromPointer(Pointer pointer, int length) {
        if (pointer == null) {
            throw new IllegalArgumentException("Pointer must not be null");
        }
        if (length < 0) {
            throw new IllegalArgumentException("Length must be non-negative");
        }

        byte[] data = new byte[length];
        pointer.read(0, data, 0, length);
        return data;
    }

    public static YrsInput[] createYrsInputArray(YInput[] array) {
        YrsInput arrayRef = new YrsInput();
        YrsInput[] nativeArray = (YrsInput[]) arrayRef.toArray(array.length);

        for (int i = 0; i< array.length; i++) {
            YrsInput input = ((YInputImpl) array[i]).getWrappedObject();
            nativeArray[i].len = input.len;
            nativeArray[i].tag = input.tag;
            nativeArray[i].value = input.value;
        }
        return nativeArray;
    }
}
