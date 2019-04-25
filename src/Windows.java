import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Windows extends JFrame{

    public Windows(){
        showWindows();
        activeClass();
        ThreadIni();
    }

    //布局内容
    private  JButton startButton, sendButton, cancelButton;
    private  JTextArea receivePlane;
    private  JTextField sendPlane;
    private  JTextField toIP, toPort;
    private  JLabel myIP, myPort;

    private sendThread sendThread;
    private receiveThread receiveThread;

    //布局
    private void showWindows(){
        //界面
        JFrame jf = new JFrame("对话框");
        jf.setSize(700,500);
        jf.getContentPane().setBackground(Color.cyan);
        jf.setVisible(true);

        //创建一个plane:jp1
        JPanel jp1 = new JPanel(new GridLayout(5,2));
        jp1.setSize(400,50);

        //myIP
        jp1.add(new JLabel("本地IP："));
        try{
            //获取本地IP
            myIP = new JLabel(Inet4Address.getLocalHost().getHostAddress());
            jp1.add(myIP);
        }catch (UnknownHostException e){
            jp1.add(new JLabel("本地IP：未知"));
        }

        //myPort
        jp1.add(new JLabel("本地对话端口："));
        myPort = new JLabel("" + 0);
        jp1.add(myPort);

        //toIP
        jp1.add(new JLabel("toIP:"));
        toIP = new JTextField();
        toIP.setColumns(0);
        jp1.add(toIP);

        //toPort
        jp1.add(new JLabel("toPort:"));
        toPort = new JTextField();
        toPort.setColumns(0);
        jp1.add(toPort);

        jp1.add(new JLabel("聊天窗口"));

        //jp1加入frame
        jp1.setOpaque(false);
        jf.add(jp1, BorderLayout.NORTH);
        jp1.setVisible(true);
        jf.setVisible(true);

        //创建第二个plane:jp2,聊天窗口,发送窗口
        JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        jp2.setSize(400,400);
        receivePlane = new JTextArea(20,60);
        receivePlane.setEditable(false);
        receivePlane.setAutoscrolls(true);
        JScrollPane jsp = new JScrollPane(receivePlane);
        jp2.add(jsp);
        jp2.add(new JLabel("发送窗口:"));
        sendPlane = new JTextField(40);
        sendPlane.setAutoscrolls(true);
        jp2.add(sendPlane);
        jf.add(jp2,BorderLayout.WEST);
        jp2.setOpaque(false);
        jp2.setVisible(true);
        jf.setVisible(true);

        //创建第三个Plane：jp3,操作按钮
        JPanel jp3 = new JPanel(new GridLayout(1,0));
        jp3.setSize(400,50);
        startButton = new JButton("startButton");
        cancelButton = new JButton("cancelButton");
        sendButton = new JButton("sendButton");
        jp3.add(startButton);
        jp3.add(cancelButton);
        jp3.add(sendButton);
        jf.add(jp3,BorderLayout.SOUTH);
        jp3.setOpaque(false);
        jp3.setVisible(true);
        jf.setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    private void activeClass(){

        //sendButton
        sendButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

                //发送文本
                sendThread.sendMes(toIP.getText(), Integer.parseInt(toPort.getText()), sendPlane.getText());
                receivePlane.setText(receivePlane.getText()  + "发送:" + sendPlane.getText()+ "\n");
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //startButton
        startButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //cancelButton
        cancelButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

                sendPlane.setText("");
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        //keyActive
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                // TODO Auto-generated method stub
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    //发送文本
                    sendThread.sendMes(toIP.getText(), Integer.parseInt(toPort.getText()), sendPlane.getText());
                    receivePlane.setText(receivePlane.getText() + "\n" + "发送:" + sendPlane.getText());
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub

            }
        });
    }

    public void ThreadIni() {
        // TODO Auto-generated method stub
        sendThread = new sendThread(0, this);
        receiveThread = new receiveThread(this);
    }


    //回调函数,用于接受从线程中返回的数据
    public void setReceive(String receive){
        receivePlane.setText(receivePlane.getText() + "收到:" + receive+ "\n" );
    }

    //当接受数据的线程开始工作以后，就调用该回调函数，设置当前聊天窗口使用的端口是哪个
    public void setLocalPort(int localPortText){
        myPort.setText(""+localPortText);
    }


    public static void main(String[] args){

        new Windows();
    }
}
