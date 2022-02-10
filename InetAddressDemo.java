import java.net.InetAddress;
import java.net.UnknownHostException;
 
public class InetAddressDemo {
    public static void main(String[] args) throws UnknownHostException {
        
        InetAddress address = InetAddress.getByName("abb-NUC10-0532");
        //获取主机名和IP地址
        String name = address.getHostName();
        String ip = address.getHostAddress();
        
        System.out.println(name + "----" + ip);
    }
}