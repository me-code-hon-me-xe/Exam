package practice;
//
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.ServerSocket;
//import java.net.Socket;
//
//
//import java.io.*;
//import java.net.*;
//
//class TCPServer {
//    public static void main(String argv[]) throws IOException {
//        int portServer = 6789;
//        ServerSocket serverSocket = new ServerSocket(portServer);
//        System.out.println("Waiting for client");
//        while(true){
//            Socket connection = serverSocket.accept();
//            System.out.println("Accept a client!");
//            BufferedReader inputFromClient = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//            String message = inputFromClient.readLine().trim();
//            System.out.println(message);
//            int number = Integer.parseInt(message);
//            double calNum = Math.sqrt(number);
//            String sendData = Double.toString(calNum);
//            DataOutputStream outToClient = new DataOutputStream(connection.getOutputStream());
//            outToClient.writeBytes(sendData);
//        }
//    }
//}
//
import java.io.*;
import java.net.*;

class TCPServer {
    public static String clientSentence;
    public static String capitalizedSentence;
    public static void main(String argv[]) throws Exception {

        try{
            ServerSocket welcomeSocket = new ServerSocket(6789);
            System.out.println("practice.Server is waiting to accept user... ");
            while(true) {

                Socket connectionSocket = welcomeSocket.accept();
                System.out.println("Accept a client!");

                Thread clientThread = new Thread(() -> {
                    try {
                        handleSocket(connectionSocket);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                clientThread.start();
            }

        }catch (IOException e){
            e.printStackTrace();
        }



    }

    public static void handleSocket(Socket connectionSocket) throws IOException {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
        DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
        clientSentence = inFromClient.readLine().trim();
        double data = Double.parseDouble(clientSentence);
        double calData = Math.sqrt(data);


        String sendData = Double.toString(calData) + "\n";
        System.out.println(sendData);
        capitalizedSentence = clientSentence.toUpperCase() + "\n";

        outToClient.writeBytes(sendData);

    }}


