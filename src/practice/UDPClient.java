package practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class UDPClient {
//    public static void main(String[] args) throws IOException {
//        int port = 9876;
//        BufferedReader inputFromUser = new BufferedReader(new InputStreamReader(System.in));
//        DatagramSocket clientSocket = new DatagramSocket();
//        InetAddress IPAddress = InetAddress.getByName("localhost");
//        while(true){
//            byte[] sendData = new byte[1024];
//            byte[] recieveData = new byte[1024];
//            System.out.println("Please enter one real number: ");
//            String message = inputFromUser.readLine();
//            sendData = message.getBytes();
//            System.out.println();
//            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
//            clientSocket.send(sendPacket);
//
//            DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
//            clientSocket.receive(recievePacket);
//            String results = new String(recievePacket.getData()).trim();
//            System.out.println("FROM SERVER:" + results);
//        }
//
//    }
public static void main(String[] args) throws IOException {
    int serverPort = 6789;
    DatagramSocket clientSocket = new DatagramSocket();
    InetAddress IPAddress = InetAddress.getByName("localhost");
    while(true){
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        System.out.println("Please enter a realNumber");
        BufferedReader inputFromUser = new BufferedReader(new InputStreamReader(System.in));
        String message  = inputFromUser.readLine();
        sendData = message.getBytes();
        DatagramPacket outToServer = new DatagramPacket(sendData, sendData.length, IPAddress, serverPort);
        clientSocket.send(outToServer);

        DatagramPacket inFromServer = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(inFromServer);
        String results = new String(inFromServer.getData()).trim();
        System.out.println("FROM SERVER: " + results);


    }}

}
