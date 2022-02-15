import java.io.*;
import java.net.*;
 
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
                byte[] buffer = new byte[512];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket_in.receive(response);
 
                String quote_msg_res = new String(buffer, 0, response.getLength());
                System.out.println("recived message: " + quote_msg_res);
                socket_in.close();
            }
        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}