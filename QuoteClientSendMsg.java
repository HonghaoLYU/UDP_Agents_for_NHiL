import java.io.*;
import java.net.*;
 
/**
 * This program demonstrates how to implement a UDP client program.
 *
 *
 * @author www.codejava.net
 */
public class QuoteClientSendMsg {
 
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Syntax: QuoteClient <hostname> <port>");
            return;
        }
 
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
 
        try {
            InetAddress address = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket();
 
            while (true) {
 
                byte[] msg_buffer = "sending msg".getBytes(); 
                DatagramPacket request = new DatagramPacket(msg_buffer, msg_buffer.length, address, port);
                socket.send(request);
 
                byte[] buffer = new byte[512];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);
 
                String quote = new String(buffer, 0, response.getLength());
 
                System.out.println(quote);
                System.out.println();
 
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