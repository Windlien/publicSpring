package com.example.publicspring.c.windLien;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) {
        // 定义服务器端口号，监听端口被接入就持续发送消息
        int port = 12345; // 端口号
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("服务器已启动，等待客户端连接...");

            // 接受客户端连接
            Socket clientSocket = serverSocket.accept();
            System.out.println("客户端已连接");

            // 获取输出流
            OutputStream outputStream = clientSocket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);

            // 模拟持续输出内容
            int count = 0;
            while (true) {
                String message = "我是消息 " + count++;
                out.println(message);
                System.out.println("发送消息: " + message);
                Thread.sleep(1000); // 每隔1秒发送一次消息
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
