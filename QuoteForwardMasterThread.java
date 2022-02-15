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
        try{
            while(true){
                new ServerThread();
                ClientThread F1 = new ClientThread("good evening, honghao");
                F1.start();
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        // DatagramSocket socket_out;
 
        // try {
        //     while (true) {
        //         socket_out = new DatagramSocket(18);
        //         byte[] msg_buffer = "good evening, honghao".getBytes(); 
        //         InetAddress address = InetAddress.getByName("192.168.30.35");
        //         int port = 18;
        //         DatagramPacket request = new DatagramPacket(msg_buffer, msg_buffer.length, address, port);
        //         socket_out.send(request);

        //         String quote_msg_req = new String(msg_buffer, 0, request.getLength());
        //         System.out.println("sending message: " + quote_msg_req);
        //         socket_out.close();
                
        //         new ServerThread();

        //         Thread.sleep(2000);
        //     }
        // } catch (SocketTimeoutException ex) {
        //     System.out.println("Timeout error: " + ex.getMessage());
        //     ex.printStackTrace();
        // } catch (IOException ex) {Thread.sleep(2000);
        //     System.out.println("Client error: " + ex.getMessage());
        //     ex.printStackTrace();
        // } catch (InterruptedExceptiThread.sleep(2000);
    }
}


// client thread
class ClientThread implements Runnable {

    Thread t;
    String dep;
    ClientThread(String data) {
       dep = data; // creat a new thread
    //    System.out.println("Client Child thread: " + dep);
    }
   
    public void run() {
		DatagramSocket socket_out;
        try {
            // while (true) {
                socket_out = new DatagramSocket(18);
                byte[] msg_buffer = dep.getBytes();
                InetAddress address = InetAddress.getByName("192.168.30.35");
                int port = 18;
                DatagramPacket request = new DatagramPacket(msg_buffer, msg_buffer.length, address, port);
                socket_out.send(request);

                String quote_msg_req = new String(msg_buffer, 0, request.getLength());
                System.out.println("sending message: " + quote_msg_req);
                socket_out.close();

                // Thread.sleep(1000);
            // }
        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        // } catch (InterruptedException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        }
        System.out.println("Exiting Client child thread.");      	
    }

    public void start() {
        if (t == null){
            t = new Thread(this, "Client Thread");
            System.out.println("Client Child thread: " + t);
            t.start();
        }
        
    }
}



// Server thread
class ServerThread implements Runnable {
    Thread t;
    ServerThread() {
       t = new Thread(this, "Server Thread"); // creat a new thread
       System.out.println("Server Child thread: " + t);
       t.start(); // start the thread
    }
   
    public void run() {
		DatagramSocket socket_in;
        try {
            // while (true) {
                socket_in = new DatagramSocket(17);
                byte[] buffer = new byte[512];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket_in.receive(response);
                String quote_msg_res = new String(buffer, 0, response.getLength());
                System.out.println("recived message: " + quote_msg_res);
                socket_in.close();
            // }
        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
        System.out.println("Exiting Server child thread.");      	
    }
}