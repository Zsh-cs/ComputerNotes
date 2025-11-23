package wechat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReaderThread extends Thread{
    private Socket socket;

    public ClientReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (true) {

                try {
                    String msg = dis.readUTF();
                    System.out.println(
                            "=====Client receives a message from another Client through Server=====\nClient Address: "
                                    + socket.getRemoteSocketAddress() +
                                    "\nMessage: " + msg + "\n");
                } catch (IOException e) {
                    System.out.println(socket.getRemoteSocketAddress() + " terminals");
                    dis.close();
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
