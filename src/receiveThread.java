import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class receiveThread extends Thread {

    private ServerSocket server;
    private Windows windows;

    public receiveThread(Windows window) {
        this.windows = window;
        try {
            server = new ServerSocket(0);
            window.setLocalPort(server.getLocalPort());
            start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Error!");
        }
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true){
            Socket socket;
            try {
                socket = server.accept();
                InputStreamReader reader = new InputStreamReader(socket.getInputStream());
                int c;
                StringBuilder sb = new StringBuilder();
                while((c = reader.read()) != -1){
                    sb.append((char)c);
                }
                windows.setReceive(sb.toString());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                System.out.println("线程将接受到的数据写入对话框出错");
            }
        }
    }

}

