package practice;

import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int serverPort = 30233;

        try {
            DatagramSocket socket = new DatagramSocket();

            BigInteger studentID = new BigInteger("2001040023");
            String studentIDString = studentID.toString();
            byte[] sendData = studentIDString.getBytes();
            InetAddress serverIP = InetAddress.getByName(serverAddress);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, serverPort);
            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String reply = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("practice.Server's reply: " + reply);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Enter a message: ");
                String message = reader.readLine();
                sendData = message.getBytes();
                sendPacket = new DatagramPacket(sendData, sendData.length, serverIP, serverPort);
                socket.send(sendPacket);

                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);
                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("practice.Server's reply: " + response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
