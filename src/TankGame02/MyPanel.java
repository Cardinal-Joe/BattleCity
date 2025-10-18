package TankGame02;

import sun.security.mscapi.CPublicKey;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * @author 光头强
 *
 * 坦克大战的绘图区域
 */

//为了响应/监听 键盘事件，实现KeyListener接口
//为了让Panel 不停的重绘子弹，需要将MyPanel，制作为线程
public class MyPanel  extends JPanel implements KeyListener,Runnable {
    //定义我的坦克
    MyTank myTank=null;
    //定义敌人坦克，放入到集合Vector中，考虑到线程
    Vector<EnemyTank> enemyTank=new Vector<>();
    //d定义一个存放Node对象的vector,用于恢复敌人坦克的坐标
    //Vector<Node> nodes=new Vector<>();

    //说明：当子弹击中了敌人坦克，加入一个Bomb对象到boms中
    //定义一个Vector集合，用于存放炸弹
    Vector<Bomb> bombs=new Vector<>();
    int enemyTankSize=7;        //数组长度

    //定义三张炸弹图片,用于显示不同的爆炸效果
    Image image01=null;
    Image image02=null;
    Image image03=null;

    public MyPanel () {     //开始新游戏还是继续游戏

        //将MyPnael对象的 enemytank 设置给Recorder的enemyTanks中
        Recorder.setEnemyTanks(enemyTank);
        myTank=new MyTank(100,200);     //初始化自己的坦克
        //enemyTank=new EnemyTank(100,100);

        //初始化敌人坦克
        for (int i = 0; i < enemyTankSize; i++) {
            //创建敌人坦克
            EnemyTank enemyTank1 = new EnemyTank(100 * (i + 1), 0);
            //将enemyTank 设置给 enemyTank1
            enemyTank1.setEnemyTank(enemyTank);
            //设置方向
            enemyTank1.setDirect(2);
            //启动敌人坦克移动线程
            new Thread(enemyTank1).start();
            //给该对象，加入一颗子弹
            shot shot=  new shot(enemyTank1.getX()+20, enemyTank1.getY()+60,enemyTank1.getDirect());
            //加入到enemyTank1 的Vector 成员中
            enemyTank1.shots.add(shot);
            //启动shot 对象
            new Thread(shot).start();

            //加入
            enemyTank.add(enemyTank1);

        }

        //初始化图片对象
        image01=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/tank01.png"));
        image02=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/tank02.png"));
        image03=Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/tank03.png"));
        myTank.setSpeed(5);
        //enemyTank.setSpeed(5);
    }

    /// 编写方法，显示我方击毁坦克的信息
    public void showInfo(Graphics g){

        //画出玩家的总成绩
        g.setColor(Color.black);
        Font font=new Font("宋体",Font.BOLD,25);
        g.setFont(font);

        g.drawString("累计击毁的坦克数量: ",1020,30);
        drawTank(1020,60,g,0,1);        //画出敌方坦克

        g.setColor(Color.black);
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,100);
    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.fillRect(0,0,1000,750);    //填充矩形,默认黑色

        showInfo(g);
        //画出坦克-封装方法,如果被销毁就不产生
        for (int i = 0; i <1; i++) {
            if (myTank.isLive){
                drawTank(myTank.getX(),myTank.getY(),g,myTank.getDirect(),0);
                //drawTank(myTank.getX()+60,myTank.getX(),g,0,1);

                //将MyTank的子弹遍历取出
                for (int j = 0; j < myTank.shots.size(); j++) {
                    //取出所有子弹
                    shot shot=myTank.shots.get(j);
                    if (shot !=null && shot.isLive){
                        g.draw3DRect(shot.x,shot.y, 5,5,false);
                    }else {
                        //移除阵亡的子弹
                        myTank.shots.remove(shot);
                        j--;    //调整索引
                    }
                }
            }else {
                myTank.isLive=false;
            }
        }


        //画出主坦克发出的子弹
        /*if (myTank.shot !=null && myTank.shot.isLive!=false){
            //g.fill3DRect(myTank.shot.x,myTank.shot.y, 5,5,false);
            g.draw3DRect(myTank.shot.x,myTank.shot.y, 5,5,false);
        }*/

        //画出炸弹爆炸效果,如果bombs集合中有炸弹，就画
        for (int i = 0; i < bombs.size(); i++) {
            //取出炸弹
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Bomb bomb = bombs.get(i);
            //根据当前bomb对象的life值去画出对应的图片
            if (bomb.life>6){
                g.drawImage(image01,bomb.x,bomb.y,60,60,this);

            } else if (bomb.life>3) {
                g.drawImage(image02,bomb.x,bomb.y,60,60,this);
            }else {
                g.drawImage(image03,bomb.x,bomb.y,60,60,this);
            }
            //炸弹生命值减少
            bomb.lifeDown();
            //如果bomb life的生命值=0，那么就从bombs 集合中删除
            if (bomb.life==0){
                bombs.remove(bomb);
            }
        }


        //画出敌人坦克-遍历Vector,当坦克被销毁时，就不产生了
        for (int i = 0; i < enemyTank.size(); i++) {
            //取出坦克
            EnemyTank tank = enemyTank.get(i);
            //判断当前坦克是否还存活,才去画出该坦克
            if (tank.isLive) {
                drawTank(tank.getX(), tank.getY(), g, tank.getDirect(), 1);

                //画出enemyTank 敌人坦克的子弹
                for (int j = 0; j < tank.shots.size(); j++) {
                    //取出子弹
                    shot shot = tank.shots.get(j);
                    //绘制子弹
                    if ( shot.isLive == true) { /// 为真
                        g.draw3DRect(shot.x, shot.y, 5, 5, false);
                    } else {
                        //子弹不存在了，直接移除
                        tank.shots.remove(shot);
                    }
                }
            }else {
                enemyTank.remove(tank);
                break;
            }
        }


    }
    //编写方法画出坦克

    /**
     *
     * @param x     坦克的左上角x坐标
     * @param y     坦克左上角的y坐标
     * @param g     画笔
     * @param direction     坦克方向
     * @param type  坦克类型(是我们操作的坦克还是敌人-enemy)
     */
    public static void drawTank(int x,int y,Graphics g,int direction ,int type ){
        //根据不同类型的坦克设置颜色
        switch (type){
            case 0:     //我们的坦克
                g.setColor(Color.pink);
                break;
            case 1:     //敌人的坦克
                g.setColor(Color.yellow);
                break;
        }

        //根据坦克的方向来绘制对应的坦克

        switch (direction){
            case 0:     //表示向上
                g.fill3DRect(x,y,10,60,false);  //坦克左边轮子
                g.fill3DRect(x+30,y,10,60,false);   //坦克右边轮子
                g.fill3DRect(x+10,y+10,20,40,false);    //坦克盖子
                g.fillOval(x+9,y+20,20,20);        //坦克圆盖
                g.fillRect(x+19,y,2,40);        //坦克炮台
                break;
            case 1:     //表示向右
                g.fill3DRect(x,y,60,10,false);  //坦克左边轮子
                g.fill3DRect(x,y+30,60,10,false);   //坦克右边轮子
                g.fill3DRect(x+10,y+10,40,20,false);    //坦克盖子
                g.fillOval(x+20,y+9,20,20);        //坦克圆盖
                g.fillRect(x+30,y+19,40,2);        //坦克炮台
                break;
            case 2:     //表示向下
                g.fill3DRect(x,y,10,60,false);  //坦克左边轮子
                g.fill3DRect(x+30,y,10,60,false);   //坦克右边轮子
                g.fill3DRect(x+10,y+10,20,40,false);    //坦克盖子
                g.fillOval(x+9,y+20,20,20);        //坦克圆盖
                g.fillRect(x+19,y+20,2,40);        //坦克炮台
                break;
            case 3:     //表示向左
                g.fill3DRect(x,y,60,10,false);  //坦克左边轮子
                g.fill3DRect(x,y+30,60,10,false);   //坦克右边轮子
                g.fill3DRect(x+10,y+10,40,20,false);    //坦克盖子
                g.fillOval(x+20,y+9,20,20);        //坦克圆盖
                g.fillRect(x-10,y+19,40,2);        //坦克炮台
                break;

            default:
                System.out.println("暂时没有处理");
        }

    }

    //编写方法-判断我方子弹是否击中了敌方坦克
    public  void hitTank( shot s,Tank tank ){         //这里可以放父类，然后动态绑定
        //判断s击中坦克
        switch (tank.getDirect()){
            case 0:     //坦克向上
            case 2:     //坦克向下
                if (s.x>tank.getX() && s.x< tank.getX()+40
                        && s.y> tank.getY() && s.y< tank.getY()+60){
                    s.isLive=false;
                    tank.isLive=false;
                    enemyTank.remove(tank);
                    //当我方坦克子弹击中了敌人坦克后，就让数据allEnemyTankNum++
                    //还要判断被击中的坦克是我方的还是敌人，如果是敌人就++，我方就不加
                    if (tank instanceof EnemyTank){
                        Recorder.allEnemyTankNum();
                    }

                    //创建一个Bomb对象，加入到bombs集合中
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);

                }
                break;
            case 1:     //向右
            case 3:     //向左
                if (s.x > tank.getX() && s.x < tank.getX()+60
                        && s.y > tank.getY() && s.y < tank.getY()+40){
                    s.isLive=false;
                    tank.isLive=false;
                    enemyTank.remove(tank);
                    //当我方坦克子弹击中了敌人坦克后，就让数据allEnemyTankNum++
                    //还要判断被击中的坦克是我方的还是敌人，如果是敌人就++，我方就不加
                    if (tank instanceof EnemyTank){
                        Recorder.allEnemyTankNum();
                    }

                    //创建一个Bomb对象，加入到bombs集合中
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);

                }
                break;

        }
    }

    //判断敌人坦克击中我方坦克
    public void hitMyTank(){

        //遍历敌人所有坦克
        for (int i = 0; i < enemyTank.size(); i++) {
            EnemyTank enemytank = enemyTank.get(i);
            //遍历敌人坦克的所有子弹
            for (int j = 0; j < enemytank.shots.size(); j++) {
                shot shot = enemytank.shots.get(j);
                if (shot.isLive && shot !=null){
                    hitTank(shot,myTank);
                }
            }

        }
    }

    //判断坦克是否被击中
    public void judgeTank(){
        //判断是否击中了敌人坦克
           /* if ( myTank.shot != null ){      //子弹活着的
                //遍历敌人的所有坦克
                for (int i = 0; i < enemyTank.size(); i++) {
                    EnemyTank tank = enemyTank.get(i);
                    hitTank(myTank.shot,tank);
                }
            }*/
        for (int i = 0; i < myTank.shots.size(); i++) {
            shot shot = myTank.shots.get(i);
            //如果子弹还存在
            if (shot !=null && shot.isLive){
                //遍历敌人所有坦克
                for (int j = 0; j < enemyTank.size(); j++) {
                    EnemyTank tank = enemyTank.get(j);
                    //敌人坦克存在
                    if (tank.isLive){
                        hitTank(shot,tank);
                    }
                    //如果子弹阵亡,
                    if (!shot.isLive){
                        break;
                    }
                }
            }else {
                //子弹不存在
                //销毁所有的子弹
                myTank.shots.remove(shot);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    //处理wdsa键按下的情况
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_W){     //向上移动
            //改变坦克的方向
            myTank.setDirect(0);
            if (myTank.getY()>0) {
                myTank.moveUp();
            }
        } else if (e.getKeyCode()==KeyEvent.VK_D) { //向右移动
            myTank.setDirect(1);
            if (myTank.getX()+60<1000) {
                myTank.moveRight();
            }
        } else if (e.getKeyCode()==KeyEvent.VK_S) {  //向下移动
            myTank.setDirect(2);
            if (myTank.getY()+60<750) {
                myTank.moveDown();
            }
        } else if (e.getKeyCode()==KeyEvent.VK_A) {  //向左移动
            myTank.setDirect(3);
            if (myTank.getX()>0) {
                myTank.moveLeft();
            }
        }

        //如果用户按下了J键，意味着坦克开始射击
        if (e.getKeyCode()==KeyEvent.VK_J){
            System.out.println("用户按下了J，开始射击");
            //判断主坦克的子弹是否存在
                myTank.ShotEnemy();
        }
        ///面板重新绘制
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {     //每隔一定的时间就要重绘
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //判断我方坦克是否击中敌人坦克
            judgeTank();
            
            //判断是否被敌人坦克击中
            hitMyTank();

            this.repaint();
        }
    }
}
