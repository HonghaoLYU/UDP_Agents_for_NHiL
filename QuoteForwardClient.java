import java.io.*;
import java.net.*;
 
/**
 * This program demonstrates how to implement a UDP client program.
 *
 *
 * @author Honghao
 */
public class QuoteForwardClient {
 
    public static void main(String[] args) {
        DatagramSocket socket_out;
 
        try {
            while (true) {

                socket_out = new DatagramSocket(18);
                byte[] msg_buffer = "good evening, honghao".getBytes(); 
                InetAddress address = InetAddress.getByName("192.168.30.35");
                int port = 18;
                DatagramPacket request = new DatagramPacket(msg_buffer, msg_buffer.length, address, port);
                socket_out.send(request);

                String quote_msg_req = new String(msg_buffer, 0, request.getLength());
                System.out.println("sending message: " + quote_msg_req);
                socket_out.close();

                // Thread.sleep(2000);
            }
        } catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}