import java.io.*;
import java.net.*;
 
/**
 * This program demonstrates how to implement a UDP client program.
 *
 *
 * @author www.codejava.net
 */
public class QuoteForwardClient {
 
    public static void main(String[] args) {
        DatagramSocket socket_in;
        DatagramSocket socket_out;
 
        try {
            String hostname = "192.168.30.35";
            int port = 18;
            InetAddress address = InetAddress.getByName(hostname);

            while (true) {
                socket_out = new DatagramSocket(18);
                byte[] msg_buffer = "sending msg".getBytes(); 
                DatagramPacket request = new DatagramPacket(msg_buffer, msg_buffer.length, address, port);
                socket_out.send(request);
                System.out.println(request);
                socket_out.close();

                socket_in = new DatagramSocket(17); 
                byte[] buffer = new byte[512];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket_in.receive(response);
 
                String quote = new String(buffer, 0, response.getLength());
 
                System.out.println(quote);
                System.out.println();
                socket_in.close();
                
                Thread.sleep(2000);
            }
 
        } catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}