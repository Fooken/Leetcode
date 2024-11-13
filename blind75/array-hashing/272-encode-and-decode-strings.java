import java.util.*;

public class Mian {
    public static void main(String[] args) {
        Codec cc = new Codec();

        // Test case 1:
        List<String> strs = new ArrayList<>();
        strs.add("hello");
        strs.add("world");
        strs.add("kai");
        

        System.out.println("Test 1: " + cc.encode(strs));

    }
}
class Codec {
    // Chunked Transfer Encoding
    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str: strs) {
            sb.append(intToString(str));
            sb.append(str);
        }
        return sb.toString();
    }

    private String intToString(String str) {
        int n = str.length();
        // 1 byte = 8 bits
        // 1 int = 4 bytes = 32 bits
        char[] bytes = new char[4];
        for (int i = 3; i >= 0; i--) {
            // 0xff = 0x11111111
            bytes[3 - i] = (char)(n >> (i * 8) & 0xff);
        }
        return new String(bytes);
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0, n = s.length();
        while (i < n) {
            int strLen = stringToInt(s.substring(i, i + 4));
            i += 4;
            res.add(s.substring(i, i + strLen));
            i += strLen;
        }
        return res;
    }

    private int stringToInt(String str) {
        int res = 0;
        for (char c: str.toCharArray()) {
            res = (res << 8) + (int)c;
        }
        return res;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strs))



/**
To convert an integer length into a 4-byte string representation, you can use bitwise operations and byte manipulation in Java. Here's an example:

java

int length = 123456;  // Example length

// Convert the length to a 4-byte string
byte[] bytes = new byte[4];
bytes[0] = (byte) (length >>> 24);
bytes[1] = (byte) (length >>> 16);
bytes[2] = (byte) (length >>> 8);
bytes[3] = (byte) length;

// Convert the byte array to a string
String lengthString = new String(bytes);

System.out.println(lengthString);  // Output: "????"

In this example, we assume the length is a positive integer. The length is converted into a 4-byte array using bitwise right shift operations (>>>) and byte type casting. The most significant byte (MSB) is stored at bytes[0], the next byte at bytes[1], and so on.

Keep in mind that converting the resulting byte array to a string may not produce readable characters since it's just a representation of the bytes. The output may appear as special characters or question marks. If you need to transmit or store the 4-byte string, you may consider encoding it using a suitable encoding scheme (e.g., Base64) to ensure proper representation and decoding when needed.
**/;
