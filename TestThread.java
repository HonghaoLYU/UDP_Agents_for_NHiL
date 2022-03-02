 
public class TestThread {
    public static void main(String[] args) {  
        final MyData data = new MyData();  
        for(int i=0;i<1;i++){  
            new Thread(new Runnable(){  
                public void run() {  
                    while (true){
                        data.add("world");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }  
            }).start();  
            new Thread(new Runnable(){                 
                public void run() { 
                    while (true) {
                        System.out.println(data.out()); 
                    }                    
                }  
              
            }).start();  
        }  
    }  
  
}
 
class MyData {  
    private String socketdata="hello";  
    public  synchronized void add(String args){  
        socketdata = socketdata + args;  
    }  
    public  synchronized String out(){
        return socketdata; 
    }  
}  