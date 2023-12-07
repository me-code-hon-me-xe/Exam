package practice;

import java.io.*;
import java.math.BigInteger;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        int port = 30233;
        try {
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("practice.Server is listening on port " + port);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                if (clientMessage.equals("2001040023")) {
                    BigInteger studentID = new BigInteger(clientMessage);

                    BigInteger result = studentID.multiply(BigInteger.valueOf(4));
                    String response = result.toString();

                    byte[] sendData = response.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                    socket.send(sendPacket);
                } else {
                    try {
                        BigInteger number = new BigInteger(clientMessage);

                        if (number.compareTo(BigInteger.ZERO) < 0) {
                            byte[] sendData = clientMessage.getBytes();
                            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                            socket.send(sendPacket);
                        } else {
                            BigInteger squared = number.pow(4);
                            String response = squared.toString();

                            byte[] sendData = response.getBytes();
                            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                            socket.send(sendPacket);
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
