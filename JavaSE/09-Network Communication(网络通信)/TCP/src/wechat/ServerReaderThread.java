package wechat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerReaderThread extends Thread {
    private Socket socket;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (true) {
                try {
                    String msg = dis.readUTF();
                    System.out.println(
                            "=====Server receives a message from a Client=====\nClient Address: "
                                    + socket.getRemoteSocketAddress() +
                                    "\nMessage: " + msg + "\n");
                    // 把服务端接收到的消息分发给所有在线的客户端
                    sendMessageToAll(msg);
                } catch (IOException e) {
                    System.out.println(socket.getRemoteSocketAddress() + " terminals");
                    Server.onlineSockets.remove(socket);
                    dis.close();
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Todo: 把服务端接收到的消息分发给所有在线的客户端
    private void sendMessageToAll(String msg) throws IOException {
        //Caution: 不要使用try-with-resources语句，会导致socket被意外关闭！
        for (Socket onlineSocket : Server.onlineSockets) {
            DataOutputStream dos = new DataOutputStream(onlineSocket.getOutputStream());
            dos.writeUTF(msg);
            dos.flush();
        }
    }
}