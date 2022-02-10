import java.io.*;
import java.net.*;
import java.util.*;
 
/**
 * This program demonstrates how to implement a UDP server program.
 *
 *
 * @author www.codejava.net
 */
public class QuoteServerBind {
    private DatagramSocket socket;
    private List<String> listQuotes = new ArrayList<String>();
    private Random random;
 
    public QuoteServerBind(int port) throws SocketException {
        System.setProperty("java.net.preferIPv4Stack" , "true");
        // NetworkInterface nif = NetworkInterface.getByName("enxa0cec8ec0096"); 
        NetworkInterface nif = NetworkInterface.getByName("eno1");
        Enumeration<InetAddress> nifAddresses = nif.getInetAddresses();
        InetSocketAddress inetAddr= new InetSocketAddress(nifAddresses.nextElement().getHostAddress(),port); 
        // String ip = nifAddresses.nextElement().getHostAddress();
        System.out.println(inetAddr);
        // System.out.println(ip);
        socket = new DatagramSocket(inetAddr);
        random = new Random();
    }
 
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Syntax: QuoteServer <file> <port>");
            return;
        }
        
        // try {
        //     NetworkInterface nif = NetworkInterface.getByName("bge0");
        //     Enumeration<InetAddress> nifAddresses = nif.getInetAddresses();
        //     InetSocketAddress inetAddr= new InetSocketAddress(nifAddresses.nextElement(),17); 
        //     InetAddress ia = InetAddress.getByName("www.demo2s.com");

        //     DatagramSocket ds = new Datagra*w  w   w  . d  e    m  o2    s .  c  o  m */mPacket(buffer, buffer.length, ia, 80);
        //     ds.connect(InetSocketAddress.createUnresolved("google.com", 8080));
        //     ds.send(dp);
        //     ds.close();
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        String quoteFile = args[0];
        int port = Integer.parseInt(args[1]);
 
        try {
            QuoteServerBind server = new QuoteServerBind(port);
            server.loadQuotesFromFile(quoteFile);
            server.service();
        } catch (SocketException ex) {
            System.out.println("Socket errrandomor: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
 
    private void service() throws IOException {
        while (true) {
            DatagramPacket request = new DatagramPacket(new byte[1], 1);
            socket.receive(request);
 
            String quote = getRandomQuote();
            byte[] buffer = quote.getBytes();
 
            InetAddress clientAddress = request.getAddress();
            int clientPort = request.getPort();
 
            DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
            socket.send(response);
        }
    }
 
    private void loadQuotesFromFile(String quoteFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(quoteFile));
        String aQuote;
 
        while ((aQuote = reader.readLine()) != null) {
            listQuotes.add(aQuote);
        }
 
        reader.close();
    }
 
    private String getRandomQuote() {
        int randomIndex = random.nextInt(listQuotes.size());
        String randomQuote = listQuotes.get(randomIndex);
        return randomQuote;
    }
}
