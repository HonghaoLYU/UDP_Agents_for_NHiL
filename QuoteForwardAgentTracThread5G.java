import java.io.*;
import java.net.*;

/**
 * This program demonstrates how to implement a UDP server program.
 *
 *
 * @author Honghao
 */
public class QuoteForwardAgentTracThread5G {

    public static void main(String[] args) throws InterruptedException {
        final MyData data = new MyData();
        DatagramSocket socket_in;
        DatagramSocket socket_out;
        try {
            socket_in = new DatagramSocket(108);
            byte[] buffer_recv = new byte[512];
            DatagramPacket request = new DatagramPacket(buffer_recv, buffer_recv.length);
            socket_in.receive(request);

            String mag_recv = new String(buffer_recv, 0, request.getLength());
            System.out.println("client request msg recived: " + mag_recv);
            socket_in.close();

            // Thread.sleep(10);

            socket_out = new DatagramSocket(107);
            byte[] buffer = mag_recv.getBytes();
            // InetAddress clientAddress = InetAddress.getByName("192.168.30.55"); // WiFi & Ethernet
            InetAddress clientAddress = InetAddress.getByName("172.16.9.47"); // 5G
            int clientPort = 107;
            DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            data.add(mag_recv);
            socket_out.send(response);

            String quote_msg_res = new String(buffer, 0, response.getLength());
            System.out.println(quote_msg_res);
            socket_out.close();
            Thread.sleep(200);
        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }

        for (int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                DatagramSocket socket_in;

                public void run() {
                    try {
                        while (true) {
                            socket_in = new DatagramSocket(108);
                            byte[] buffer_recv = new byte[512];
                            DatagramPacket request = new DatagramPacket(buffer_recv, buffer_recv.length);
                            socket_in.receive(request);

                            String mag_recv = new String(buffer_recv, 0, request.getLength());
                            System.out.println("client request msg recived: " + mag_recv);
                            data.add(mag_recv);
                            socket_in.close();
                        }
                    } catch (SocketException ex) {
                        System.out.println("Socket error: " + ex.getMessage());
                    } catch (IOException ex) {
                        System.out.println("I/O error: " + ex.getMessage());
                    }
                }
            }).start();

            new Thread(new Runnable() {
                DatagramSocket socket_out;

                public void run() {
                    try {
                        while (true) {
                            socket_out = new DatagramSocket(107);
                            byte[] buffer = data.out().getBytes();
                            // InetAddress clientAddress = InetAddress.getByName("192.168.30.55"); // WiFi & Ethernet
                            InetAddress clientAddress = InetAddress.getByName("172.16.9.47"); // 5G
                            int clientPort = 107;
                            DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress,
                                    clientPort);
                            socket_out.send(response);

                            String quote_msg_res = new String(buffer, 0, response.getLength());
                            System.out.println(quote_msg_res);
                            socket_out.close();
                        }
                    } catch (SocketException ex) {
                        System.out.println("Socket error: " + ex.getMessage());
                    } catch (IOException ex) {
                        System.out.println("I/O error: " + ex.getMessage());
                    }
                }
            }).start();
        }
    }
}

class MyData {
    private String socketdata = "";

    public synchronized void add(String args) {
        socketdata = args;
    }

    public synchronized String out() {
        return socketdata;
    }
}