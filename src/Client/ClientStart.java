package Client;

import Controller.Music;
import Panel.Login;
import javazoom.jl.player.Player;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientStart {
    public static String ip;
    public static DataInputStream dis;
    public static DataOutputStream dos;

    ClientStart(Socket socket) {
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ip = addr.getHostAddress();
        try {
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(ip + ":0002");
            new Login();
			new Thread(Music::new).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void OutStreamAll(String info) {

        try {
            dos.writeUTF(info);
        } catch (Exception e) {
            System.out.println("ClientStart.OutStreamAll()");
        }
    }

    public static void main(String[] args) throws UnknownHostException {

        Socket socket;
        InetAddress addr = InetAddress.getLocalHost();
        ip = addr.getHostAddress();
        try {
//			socket = new Socket("10.200.85.158", 8089);
//			socket = new Socket("10.200.120.155", 8089);
//			socket = new Socket("10.114.11.14", 8088);
//			socket = new Socket("39.105.34.165", 80);
			socket = new Socket("192.168.0.107", 8080);
//			socket = new Socket("localhost", 8089);
            new ClientStart(socket);
        } catch (IOException e) {
			System.out.println("服务器未运行");
        }
    }
}