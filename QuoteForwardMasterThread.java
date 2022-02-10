import java.io.*;
import java.net.*;
 
/**
 * This program demonstrates how to implement a UDP client program.
 *
 *
 * @author Honghao
 */
public class QuoteForwardMasterThread {
 
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
                System.out.println(quote_msg_req);
                socket_out.close();
                
                new NewThread();

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

// client thread
class NewThread implements Runnable {
    Thread t;
    NewThread() {
       t = new Thread(this, "Demo Thread"); // creat a new thread
       System.out.println("Child thread: " + t);
       t.start(); // start the thread
    }
   
    public void run() {
		DatagramSocket socket_in;
       	try {
			socket_in = new DatagramSocket(17);
			byte[] buffer = new byte[512];
			DatagramPacket response = new DatagramPacket(buffer, buffer.length);
			socket_in.receive(response);
			String quote_msg_res = new String(buffer, 0, response.getLength());
			System.out.println("recived message" + quote_msg_res);
			socket_in.close();
		}
        catch (Exception e) {
            e.printStackTrace();
        }
      	System.out.println("Exiting child thread.");
    }
}