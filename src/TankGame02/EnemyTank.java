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
    //增加一个成员 EnemyTank 可以得到敌人坦克的Vector
    Vector<EnemyTank> enemyTank=new Vector<>();
    boolean isLive=true;
    public EnemyTank(int x, int y) {
        super(x, y);
    }

    //该方法可以将MyPanel 对象的属性Vector<EnemyTank> enemyTank=new Vector<>();，设置到我们的EnemyTank成员中
    public void setEnemyTank(Vector<EnemyTank> enemyTank) {
        this.enemyTank = enemyTank;
    }

    //该方法可以判断 当前敌人坦克与 enemyTank中的坦克是否发生碰撞或者重叠
    public boolean isTouchEnemyTank(){
        /// 判断当前坦克的方向
        switch (this.getDirect()){
            case 0: //向上
                //让当前的敌人坦克和其他的剩余的敌人坦克进行比较，查看是否重叠
                for (int i = 0; i < enemyTank.size(); i++) {
                    //从Vector中 取出一个敌人坦克
                    EnemyTank enemyTank1 = enemyTank.get(i);
                    //注意：不和自己比较
                    if (enemyTank1 !=this){
                        //如果敌人坦克方向是上或者下
                        if (enemyTank1.getDirect()==0 || enemyTank1.getDirect()==2){
                            //左上角
                            if (this.getX()>= enemyTank1.getX()
                                    && this.getX()<=enemyTank1.getX()+40
                                    && this.getY()>= enemyTank1.getY()
                                    && this.getY()<=enemyTank1.getY()+60){
                                return true;        //满足则发生碰撞
                            }
                            //右上角
                            if (this.getX()+40>= enemyTank1.getX()
                                    && this.getX()+40<=enemyTank1.getX()+40
                                    && this.getY()>= enemyTank1.getY()
                                    && this.getY()<=enemyTank1.getY()+60){
                                return true;        //满足则发生碰撞
                            }
                        }
                        //如果敌人坦克方向是右或者左
                        if (enemyTank1.getDirect()==1 || enemyTank1.getDirect()==3){
                            if (this.getX()>= enemyTank1.getX()
                                    && this.getX()<=enemyTank1.getX()+60
                                    && this.getY()>= enemyTank1.getY()
                                    && this.getY()<=enemyTank1.getY()+40){
                                return true;        //满足则发生碰撞
                            }
                            if (this.getX()+40>= enemyTank1.getX()
                                    && this.getX()+40<=enemyTank1.getX()+60
                                    && this.getY()>= enemyTank1.getY()
                                    && this.getY()<=enemyTank1.getY()+40){
                                return true;        //满足则发生碰撞
                            }
                        }
                    }
                }
                break;
            case 1: //向右
                //让当前的敌人坦克和其他的剩余的敌人坦克进行比较，查看是否重叠
                for (int i = 0; i < enemyTank.size(); i++) {
                    //从Vector中 取出一个敌人坦克
                    EnemyTank enemyTank1 = enemyTank.get(i);
                    //注意：不和自己比较
                    if (enemyTank1 !=this){
                        //如果敌人坦克方向是上或者下
                        if (enemyTank1.getDirect()==0 || enemyTank1.getDirect()==2){
                            //右上角
                            if (this.getX()+60>= enemyTank1.getX()
                                    && this.getX()<=enemyTank1.getX()+40
                                    && this.getY()>= enemyTank1.getY()
                                    && this.getY()<=enemyTank1.getY()+60){
                                return true;        //满足则发生碰撞
                            }
                            //右下角
                            if (this.getX()+60>= enemyTank1.getX()
                                    && this.getX()+60<=enemyTank1.getX()+40
                                    && this.getY()+40>= enemyTank1.getY()
                                    && this.getY()+40<=enemyTank1.getY()+60){
                                return true;        //满足则发生碰撞
                            }
                        }
                        //如果敌人坦克方向是右或者左
                        if (enemyTank1.getDirect()==1 || enemyTank1.getDirect()==3){
                            if (this.getX()+60>= enemyTank1.getX()
                                    && this.getX()+60<=enemyTank1.getX()+60
                                    && this.getY()>= enemyTank1.getY()
                                    && this.getY()<=enemyTank1.getY()+40){
                                return true;        //满足则发生碰撞
                            }
                            if (this.getX()+60>= enemyTank1.getX()
                                    && this.getX()+60<=enemyTank1.getX()+60
                                    && this.getY()+40>= enemyTank1.getY()
                                    && this.getY()+40<=enemyTank1.getY()+40){
                                return true;        //满足则发生碰撞
                            }
                        }
                    }
                }
                break;
            case 2: //向下
                //让当前的敌人坦克和其他的剩余的敌人坦克进行比较，查看是否重叠
                for (int i = 0; i < enemyTank.size(); i++) {
                    //从Vector中 取出一个敌人坦克
                    EnemyTank enemyTank1 = enemyTank.get(i);
                    //注意：不和自己比较
                    if (enemyTank1 !=this){
                        //如果敌人坦克方向是上或者下
                        if (enemyTank1.getDirect()==0 || enemyTank1.getDirect()==2){
                            //左下角
                            if (this.getX()>= enemyTank1.getX()
                                    && this.getX()<=enemyTank1.getX()+40
                                    && this.getY()+60>= enemyTank1.getY()
                                    && this.getY()+60<=enemyTank1.getY()+60){
                                return true;        //满足则发生碰撞
                            }
                            //右下角
                            if (this.getX()+40>= enemyTank1.getX()
                                    && this.getX()+40<=enemyTank1.getX()+40
                                    && this.getY()+60>= enemyTank1.getY()
                                    && this.getY()+60<=enemyTank1.getY()+60){
                                return true;        //满足则发生碰撞
                            }
                        }
                        //如果敌人坦克方向是右或者左
                        if (enemyTank1.getDirect()==1 || enemyTank1.getDirect()==3){
                            //左下角
                            if (this.getX()>= enemyTank1.getX()
                                    && this.getX()<=enemyTank1.getX()+60
                                    && this.getY()+60>= enemyTank1.getY()
                                    && this.getY()+60<=enemyTank1.getY()+40){
                                return true;        //满足则发生碰撞
                            }
                            if (this.getX()+40>= enemyTank1.getX()
                                    && this.getX()+40<=enemyTank1.getX()+60
                                    && this.getY()+60>= enemyTank1.getY()
                                    && this.getY()+60<=enemyTank1.getY()+40){
                                return true;        //满足则发生碰撞
                            }
                        }
                    }
                }
                break;
            case 3: //向左
                //让当前的敌人坦克和其他的剩余的敌人坦克进行比较，查看是否重叠
                for (int i = 0; i < enemyTank.size(); i++) {
                    //从Vector中 取出一个敌人坦克
                    EnemyTank enemyTank1 = enemyTank.get(i);
                    //注意：不和自己比较
                    if (enemyTank1 !=this){
                        //如果敌人坦克方向是上或者下
                        if (enemyTank1.getDirect()==0 || enemyTank1.getDirect()==2){
                            //左上角
                            if (this.getX()>= enemyTank1.getX()
                                    && this.getX()<=enemyTank1.getX()+40
                                    && this.getY()>= enemyTank1.getY()
                                    && this.getY()<=enemyTank1.getY()+60){
                                return true;        //满足则发生碰撞
                            }
                            //左下角
                            if (this.getX()>= enemyTank1.getX()
                                    && this.getX()<=enemyTank1.getX()+40
                                    && this.getY()+40>= enemyTank1.getY()
                                    && this.getY()+40<=enemyTank1.getY()+60){
                                return true;        //满足则发生碰撞
                            }
                        }
                        //如果敌人坦克方向是右或者左
                        if (enemyTank1.getDirect()==1 || enemyTank1.getDirect()==3){
                            //左上角
                            if (this.getX()>= enemyTank1.getX()
                                    && this.getX()<=enemyTank1.getX()+60
                                    && this.getY()>= enemyTank1.getY()
                                    && this.getY()<=enemyTank1.getY()+40){
                                return true;        //满足则发生碰撞
                            }
                            //左下角
                            if (this.getX()>= enemyTank1.getX()
                                    && this.getX()<=enemyTank1.getX()+60
                                    && this.getY()+40>= enemyTank1.getY()
                                    && this.getY()+40<=enemyTank1.getY()+40){
                                return true;        //满足则发生碰撞
                            }
                        }
                    }
                }
                break;
        }
        return false;
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
                        if (getY()>0 && ! isTouchEnemyTank()) {
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
                        if ( getX()+60 <100 && ! isTouchEnemyTank()){
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
                        if (getY()+60<750 && ! isTouchEnemyTank()) {
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
                        if (getX()+60>0 && ! isTouchEnemyTank()) {
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
