package TankGame02;

import java.util.Collections;
import java.util.Vector;

/**
 * @author 光头强
 *
 * 主角
 */
public class MyTank extends Tank {

    Vector<shot> shots=new Vector<>();
    private static final int Max_Shots=5;


    public MyTank(int x, int y) {
        super(x, y);
    }

    //开火方法
    public void ShotEnemy(){
        //定义shot对象,表示射击
        shot shot=null;
        //创建shot对象,根据当前主坦克的方向和位置来设计子弹方向
        switch (getDirect()){   //得到坦克方向
            case 0:
                shot = new shot(getX()+20,getY(),0);
                break;
            case 1:
                shot = new shot(getX()+60,getY()+20,1);
                break;
            case 2:
                shot = new shot(getX()+20,getY()+60,2);
                break;
            case 3:
                shot = new shot(getX(),getY()+20,3);
                break;
        }
        if (shot !=null){
            shots.add(shot);
            /// 启动shot线程
            new Thread(shot).start();
        }
        if (shots.size()>=Max_Shots){
            return;
        }

    }


}
