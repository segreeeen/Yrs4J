package at.yrs4j.utils;

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
}
