package lesson01;

import javax.swing.*;
import java.awt.*;
import java.time.Year;

/**
 * @author 光头强
 *
 * 演示如何在java中画圆
 */
@SuppressWarnings({"all"})
public class lesson01_Draw01 extends JFrame{        //JFrame对应的窗口，理解为一个画框
    //定义一个面版
    private  MyPanel mp=null;
    public static void main(String[] args) {

        new  lesson01_Draw01();
    }
    public  lesson01_Draw01(){      //构造器
        //初始化面板
        mp=new MyPanel();
        //把面板放入到窗口中
        this.add(mp);
        //设置窗口大小
        this.setSize(400,300);
        //退出程序,当点击窗口的退出按钮，即程序退出
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //面板可视化,可以显示
        this.setVisible(true);
    }
}
//1. 先定义一个panel-面板,去继承JPanel类,画图形就增长这个画版上画
class MyPanel extends JPanel {

    //1. MyPanel 对象就是一个画板
    //2. Graphics g 把 g 理解为一支画笔
    //3. Graphics  提供了很多的方法
    //注意:x-要绘制的椭圆的左上角的x坐标
        //y-要绘制的椭圆的左上角的y坐标
        //weight-要绘制的椭圆的宽度
        //height-要绘制的椭圆的高度
    @Override
    public void paint(Graphics g) {     //用来绘图的方法

        //注意：当组件第一次在屏幕显示的时候，程序会自动地调用paint()方法
        ///注意：在以下情况paint()方法将会被调用
        //1. 窗口最小化，再最大化
        //2. 窗口的大小发生变化
        //3. repaint函数被调用
        super.paint(g);     //调用父类方法去完成初始化
        //画一个圆
        //g.drawOval(10,10,100,100);

        //演示画出不同的图形
        //1. 画直线 drawLine(int x1,int  y1,int  x2,int  y2) 起点 x1，x2，终点 y1，y2
        //g.drawLine(10,10,100,100);
        //2. 画矩形边框 drawRect(int  x,int  y, int  width,int  height)
        g.drawRect(10,10,100,100);
        //3. 画椭圆边框 drawOval(int x,int y ,int width,int  height)
        //4. 填充距形 fillRect (int  x,int  y，int width,int  height)
            //先设置画笔颜色
        g.setColor(Color.red);
        g.fillRect(10,10,100,100);

        //5. 填充椭圆 fillOval(int x，int y，int width,int height)
        //g.fillOval(10,10,100,100);
        //6. 画图片drawImage（Image img,int  x,int  y,  ....).
            //1. 加载图片资源
        Image image= Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/【哲风壁纸】街景-风景.png"));
        g.drawImage(image,10,10,1197,1148,this);
        //7. 画字符串drawString(string str,int  x,int  y)
            //给画笔设置字体和颜色
        g.setColor(Color.pink);
        g.setFont(new Font("宋体",Font.BOLD,20));
        g.drawString("自由真好",100,100);
        //8. 设置画笔的字体setFont(Font  font)
        //9. 设置画笔的颜色setColor(Color  c)
    }
}