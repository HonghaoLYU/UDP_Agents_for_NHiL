import java.io.*;
import java.net.*;
import java.util.*;
 
/**
 * This program demonstrates how to implement a UDP client program.
 *
 *
 * @author www.codejava.net
 */
public class QuoteClientBind {
 
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Syntax: QuoteClient <hostname> <port>");
            return;
        }
 
        String hostname = args[0];
        int port = Integer.parseInt(args[1]);
 
        try {
            System.setProperty("java.net.preferIPv4Stack" , "true");
            // NetworkInterface nif = NetworkInterface.getByName("enxa0cec8ec0096"); 
            NetworkInterface nif = NetworkInterface.getByName("eno1");
            Enumeration<InetAddress> nifAddresses = nif.getInetAddresses();
            InetSocketAddress inetAddr= new InetSocketAddress(nifAddresses.nextElement().getHostAddress(),port); 
            DatagramSocket socket = new DatagramSocket(inetAddr);

            InetAddress address = InetAddress.getByName(hostname);
            System.out.println(address);
 
            while (true) {
 
                DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);
                System.out.println(request);
                socket.send(request);
 
                byte[] buffer = new byte[512];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);
 
                String quote = new String(buffer, 0, response.getLength());
 
                System.out.println(quote);
                System.out.println();
 
                Thread.sleep(10000);
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
