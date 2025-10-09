package TankGame02;

/**
 * @author 光头强
 *
 * 射击子弹
 */
public class shot implements Runnable{

    int x;  //子弹横坐标
    int y;  //子弹纵坐标
    int direct=0;   //子弹方向
    int speed=2;    //子弹速度
    boolean isLive=true;    //判断子弹是否还在

    //构造器


    public shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {     //射击
        while (true){

            //子弹休眠
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //子弹方向
            switch (direct){
                case 0:
                    y-=speed;
                    break;
                case 1:     //向右
                    x+=speed;
                    break;
                case 2:     //向下
                    y+=speed;
                    break;
                case 3:
                    x-=speed;
                    break;
            }

            //当子弹移动到面板的边界时，就应该自动销毁
            //当子弹碰到了敌人坦克也应该自动销毁
            if (!(x>=0 && x<=1000 && y>=0 && y<=750 && isLive)){      //碰到了边界
                isLive=false;
                break;
            }
        }

    }
}
