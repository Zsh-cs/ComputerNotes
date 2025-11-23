package wechat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        Socket socket = new Socket(Server.IP, Server.PORT);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());// 把低级流包装成高级流
        // Todo: 创建一个独立的线程，负责随时从socket中接收服务端发送过来的信息
        new ClientReaderThread(socket).start();

        while (true) {
            System.out.println("Please input a message:");
            String msg = sc.nextLine();
            if (msg.equals("exit")) {
                System.out.println("Client exits successfully!");
                dos.close();
                socket.close();
                break;
            }
            dos.writeUTF(msg);
            dos.flush();
        }
    }
}
