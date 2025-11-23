package simpleBS;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class ServerReaderThread extends Thread {
    private Socket socket;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            PrintStream ps=new PrintStream(socket.getOutputStream());
            ps.println("HTTP/1.1 200 OK");
            ps.println("Content-Type:text/html;charset=UTF-8");
            ps.println();
            ps.println("<div style='color:red;font-size:160px;text-align:center'>黑马程序员666<div>");

            // 数据响应完后立即关闭资源

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}