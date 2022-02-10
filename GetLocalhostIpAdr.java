import java.net.*;
import java.io.*;
 
/**
 * This program demonstrates a simple TCP/IP socket client.
 *
 * @author www.codejava.net
 */
public class GetLocalhostIpAdr {
 
    public static void main(String[] args) throws UnknownHostException {
        InetAddress localhost = InetAddress.getLocalHost();
        System.out.println(localhost);
    }
}
