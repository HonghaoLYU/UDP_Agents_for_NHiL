import java.io.*;
import java.net.*;
import java.util.*;
 
/**
 * This program demonstrates how to implement a UDP server program.
 *
 *
 * @author Honghao
 */
public class QuoteForwardServer {

    public static void main(String[] args) {
        DatagramSocket socket_in;
 
        try {
            while (true) {
                socket_in = new DatagramSocket(17);
                byte[] buffer_recv = new byte[512];
                DatagramPacket request = new DatagramPacket(buffer_recv, buffer_recv.length);
                socket_in.receive(request);
                String mag_recv = new String(buffer_recv, 0, request.getLength());
                System.out.println("client request msg recived from 17: " + mag_recv);
                socket_in.close();
            }
        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}