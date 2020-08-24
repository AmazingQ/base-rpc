package top.ethink;

/**
 * @author AmazingQ
 * @date 2020/8/22 14:24
 */
public class TestInt {
    public static void main(String[] args) {
        byte[] bytes = intToBytes(55);
        int i = bytesToInt(bytes, 0);
        System.out.println(i);
    }

    public static byte[] intToBytes(int n) {
        byte[] buf = new byte[4];
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) (n >> (8 * i));
        }
        return buf;
    }

    public static int bytesToInt(byte[] buf, int offset) {
        return buf[offset] & 0xff
                | ((buf[offset + 1] << 8) & 0xff00)
                | ((buf[offset + 2] << 16) & 0xff0000)
                | ((buf[offset + 3] << 24) & 0xff000000);
    }
}
