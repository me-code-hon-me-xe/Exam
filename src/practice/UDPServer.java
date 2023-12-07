package practice;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

class UDPServer {
//    public static void main(String args[]) throws Exception {
//       int port = 9876;
//       DatagramSocket serverSocket = new DatagramSocket(port);
//        System.out.println("practice.Server is running...");
//       while(true) {
//           byte[] sendData = new byte[1024];
//           byte[] recieveData = new byte[1024];
//           DatagramPacket recievePacket = new DatagramPacket(recieveData, recieveData.length);
//           serverSocket.receive(recievePacket);
//           String messageFromUser = new String(recievePacket.getData());
//           Double modifiedValue = Double.parseDouble(messageFromUser);
//           Double calValue = Math.pow(modifiedValue, 3);
//           String sendMessage = String.valueOf(calValue);
//           sendData = sendMessage.getBytes();
//
//           InetAddress IPAddress = recievePacket.getAddress();
//           int clientPort = recievePacket.getPort();
//           DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress, clientPort);
//           serverSocket.send(sendPacket);
//       }
//    }
public static void main(String[] args) throws IOException {
    int port = 6789;
    DatagramSocket serverSocket = new DatagramSocket(port);
    String receiveMessage;
    System.out.println("practice.Server is now listening on port: " + port);
    while(true){
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];

        DatagramPacket inFromClient = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(inFromClient);
        receiveMessage = new String(inFromClient.getData()).trim();
        int modifiedMessage = Integer.parseInt(receiveMessage);
        double calMessage = Math.sqrt(modifiedMessage);
        sendData =  Double.toString(calMessage).getBytes();

        InetAddress IPAddress = inFromClient.getAddress();
        int portClient = inFromClient.getPort();
        System.out.println("practice.Client port" + portClient);
        DatagramPacket outToClient = new DatagramPacket(sendData, sendData.length, IPAddress, portClient);
        serverSocket.send(outToClient);



    }}
}
