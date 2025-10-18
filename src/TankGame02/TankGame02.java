package TankGame02;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author 光头强
 */
public class TankGame02 extends JFrame {

    //定义一个MyPanel
    MyPanel mp=null;
    //static Scanner scanner =new Scanner(System.in);
    public static void main(String[] args) {


        TankGame02 tankGame02 = new TankGame02();

    }

    public TankGame02()  {
        mp=new MyPanel();
        this.add(mp);   //把面板即绘图游戏区
        new Thread(mp).start();     //将MyPanel的对象mp，放入到线程中
        this.setSize(1300,900);
        this.addKeyListener(mp);    //增加监听者，监听mp的键盘事件
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


        //在JFrame中，增加相应的关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("监听到关闭窗口指令");
                Recorder.saveRecord();
                System.exit(0);
            }
        });
    }
}
