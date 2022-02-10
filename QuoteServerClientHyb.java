import java.io.*;
import java.net.*;
import java.util.*;
 
/**
 * This program demonstrates how to implement a UDP server program.
 *
 *
 * @author www.codejava.net
 */

// // 创建一个新的线程
// class NewThread implements Runnable {
//     Thread t;
//     NewThread() {
//        // 创建第二个新线程
//        t = new Thread(this, "Demo Thread");
//        System.out.println("Child thread: " + t);
//        t.start(); // 开始线程
//     }
   
//     // 第二个线程入口
//     public void run() {
//        try {
//           for(int i = 5; i > 0; i--) {
//              System.out.println("Child Thread: " + i);
//              // 暂停线程
//              Thread.sleep(5000);
//           }
//       } catch (InterruptedException e) {
//           System.out.println("Child interrupted.");
//       }
//       System.out.println("Exiting child thread.");
//     }
//  }

// 创建一个新的client线程
class NewThread implements Runnable {
    Thread t;
    NewThread() {
       // 创建第二个新线程
       t = new Thread(this, "Demo Thread");
       System.out.println("Child thread: " + t);
       t.start(); // 开始线程
    }
   
    // 第二个线程入口
    public void run() {
       try {
            while (true) {
                Thread.sleep(1000);
                InetAddress address = InetAddress.getByName("localhost");
                DatagramSocket socket = new DatagramSocket();
                System.out.println(address);
                DatagramPacket request = new DatagramPacket(new byte[1], 1, address, 17);
                socket.send(request);
                byte[] buffer = new byte[512];
                DatagramPacket response = new DatagramPacket(buffer, buffer.length);
                socket.receive(response);
                String quote = new String(buffer, 0, response.getLength());
                System.out.println(quote);
                System.out.println();
            }
       }
        catch (Exception e) {
            e.printStackTrace();
        }
      System.out.println("Exiting child thread.");
    }
}

public class QuoteServerClientHyb {
    // private DatagramSocket socket;
    private List<String> listQuotes = new ArrayList<String>();
    private Random random;
 
    // public QuoteServerClientHyb(int port) throws SocketException {
    //     socket = new DatagramSocket(port);
    //     random = new Random();
    // }
 
    // public static void client(String[] args) {
    //     if (args.length < 2) {
    //         System.out.println("Syntax: QuoteClient <hostname> <port>");
    //         return;
    //     }
 
    //     String hostname = args[0];
    //     int port = Integer.parseInt(args[1]);
 
    //     try {
    //         InetAddress address = InetAddress.getByName(hostname);
    //         DatagramSocket socket = new DatagramSocket();
    //         System.out.println(address);
 
    //         while (true) {
 
    //             DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);
    //             System.out.println(request);
    //             socket.send(request);
 
    //             byte[] buffer = new byte[512];
    //             DatagramPacket response = new DatagramPacket(buffer, buffer.length);
    //             socket.receive(response);
 
    //             String quote = new String(buffer, 0, response.getLength());
 
    //             System.out.println(quote);
    //             System.out.println();
 
    //             Thread.sleep(10000);
    //         }
 
    //     } catch (SocketTimeoutException ex) {
    //         System.out.println("Timeout error: " + ex.getMessage());
    //         ex.printStackTrace();
    //     } catch (IOException ex) {
    //         System.out.println("Client error: " + ex.getMessage());
    //         ex.printStackTrace();
    //     } catch (InterruptedException ex) {
    //         ex.printStackTrace();
    //     }
    // }

    public static void main(String[] args) {

        DatagramSocket socket;
        new NewThread(); // test for a new thread

        if (args.length < 2) {
            System.out.println("Syntax: QuoteServer <file> <port>");
            return;
        }
 
        // String quoteFile = args[0];
        // int port = Integer.parseInt(args[1]);
 
        try {
            // QuoteServerClientHyb server = new QuoteServerClientHyb(port);
            // server.loadQuotesFromFile(quoteFile);
            // server.service();
            while (true) {
                socket = new DatagramSocket(17);
                DatagramPacket request = new DatagramPacket(new byte[1], 1);
                socket.receive(request);
    
                String quote = "first message";
                byte[] buffer = quote.getBytes();

                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();
    
                DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                socket.send(response);
                socket.close();
            }
        } catch (SocketException ex) {
            System.out.println("Socket error: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
 
//     private void service() throws IOException {
//         while (true) {
//             DatagramPacket request = new DatagramPacket(new byte[1], 1);
//             socket.receive(request);
 
//             String quote = getRandomQuote();
//             byte[] buffer = quote.getBytes();
 
//             InetAddress clientAddress = request.getAddress();
//             int clientPort = request.getPort();
 
//             DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
//             socket.send(response);
//         }
//     }
 
//     private void loadQuotesFromFile(String quoteFile) throws IOException {
//         BufferedReader reader = new BufferedReader(new FileReader(quoteFile));
//         String aQuote;
 
//         while ((aQuote = reader.readLine()) != null) {
//             listQuotes.add(aQuote);
//         }
 
//         reader.close();
//     }
 
//     private String getRandomQuote() {
//         int randomIndex = random.nextInt(listQuotes.size());
//         String randomQuote = listQuotes.get(randomIndex);
//         return randomQuote;
//     }
}
