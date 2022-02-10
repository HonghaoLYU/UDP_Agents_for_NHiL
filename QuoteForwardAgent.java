import java.io.*;
import java.net.*;
 
/**
 * This program demonstrates how to implement a UDP server program.
 *
 *
 * @author Honghao
 */
public class QuoteForwardAgent {

    public static void main(String[] args) {
        DatagramSocket socket_in;
        DatagramSocket socket_out;
 
        try {
            while (true) {
                
                socket_in = new DatagramSocket(18);
                byte[] buffer_recv = new byte[512];
                DatagramPacket request = new DatagramPacket(buffer_recv, buffer_recv.length);
                socket_in.receive(request);

                String mag_recv = new String(buffer_recv, 0, request.getLength());
                System.out.println("client request msg recived: " + mag_recv);
                socket_in.close();
                
                socket_out = new DatagramSocket(18);
                byte[] buffer = mag_recv.getBytes();
                InetAddress clientAddress = InetAddress.getByName("192.168.30.55");
                int clientPort = 17;
                DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
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
}