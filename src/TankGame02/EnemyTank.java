package TankGame02;

import java.util.Vector;

/**
 * @author 光头强
 *
 * 敌人坦克
 */
public class EnemyTank extends Tank implements Runnable{
    //敌人坦克,使用Vector 保存多个Shot
    Vector<shot> shots=new Vector<>();
    boolean isLive=true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {
        while (true){

            //判断敌人坦克是否存在：
            if (shots.size()<5 && isLive){      //敌人坦克没有子弹了,并且敌人坦克活着
                shot shot=null;
                //获取方向，并得到对应方向的子弹
                switch (getDirect()){
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
                shots.add(shot);
                new Thread(shot).start();
            }


            //根据坦克的方向来继续移动
            switch (getDirect()){
                case 0:
                    for (int i = 0; i < 25; i++) {
                        if (getY()>0) {
                            moveUp();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 25; i++) {
                        if ( getX()+60 <100){
                            moveRight();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 25; i++) {
                        if (getY()+60<750) {
                            moveDown();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 25; i++) {
                        if (getX()+60>0) {
                            moveLeft();
                        }
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }

            //休眠：
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //随机的改变方向
            setDirect((int) (Math.random() * 4));
            //声明时候退出线程
            if (isLive==false){
                break;      //退出线程
            }
        }
    }
}
