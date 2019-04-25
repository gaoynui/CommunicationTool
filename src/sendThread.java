import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class sendThread {

    Windows Windows;
    private String toIP = "";
    private int toPort = 0;
    private String sendPlane = "";

    public sendThread(int port, Windows window) {
        Windows = window;
    }

    public void notRun() {
        InetSocketAddress isa = new InetSocketAddress(toIP, toPort);
        try {
            Socket socket = new Socket();
            socket.connect(isa);
            OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(sendPlane);
            writer.flush();
            writer.close();
            System.out.println("将数据写入到流中");
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            sendPlane = "";
        }
    }

    public void sendMes(String host,int port,String message){
        toIP = host;
        this.toPort = port;
        this.sendPlane = message;
        notRun();
    }

}
