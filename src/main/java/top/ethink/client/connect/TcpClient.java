package top.ethink.client.connect;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author AmazingQ
 * @date 2020/8/22 13:34
 */
public class TcpClient {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static int MAX_PACKAGE_SIZE = 1024 * 4;
    private static String SERVER_IP = "127.0.0.1";
    private static int SERVER_PORT = 58885;
    private static TcpClient instance = null;
    private boolean isInit = false;
    SocketChannel client = null;

    public TcpClient() {

    }

    /**
     * 初始化TcpClient
     *
     * @return
     */
    public static TcpClient getInstance() {
        if (instance == null) {
            instance = new TcpClient();
        }
        return instance;
    }

    /**
     * 初始化 Socket
     * @throws IOException
     */
    public void init() throws IOException {
        if (!isInit) {
            client = SocketChannel.open(new InetSocketAddress(SERVER_IP, SERVER_PORT));
            client.configureBlocking(true);
        }
        isInit = true;
    }

    /**
     * 发送数据
     * @param data
     * @return
     */
    public boolean sendData(byte[] data){
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        byteBuffer.put(data);
        byteBuffer.flip();
        int ret = 0;
        try {
            ret = client.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 接受数据
     * @return
     */
    public byte[] recvData()  {
        ByteBuffer byteBuffer = ByteBuffer.allocate(MAX_PACKAGE_SIZE);
        try {
            int rs = client.read(byteBuffer);
            byte[] bytes = new byte[rs];
            byteBuffer.flip();
            byteBuffer.get(bytes);
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
