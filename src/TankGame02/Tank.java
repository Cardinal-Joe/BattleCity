package TankGame02;

import javax.print.attribute.standard.PresentationDirection;

/**
 * @author 光头强
 */
public class Tank {
    private  int x; //坦克的横坐标
    private  int y; //坦克的纵坐标
    private  int direct;  //坦克方向  up、down、left、right
    private  int speed=1;     //坦克的速度
    boolean isLive=true;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;

    }


    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    //移动获取方法
    public void moveUp(){
        y-=speed;
    }
    public void moveRight(){
        x+=speed;
    }
    public void moveDown(){
        y+=speed;
    }
    public void moveLeft(){
        x-=speed;
    }

}
