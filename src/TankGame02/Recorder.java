package TankGame02;

import java.io.*;
import java.util.Vector;

/**
 * @author 光头强
 *
 * 该类用于记录相关的信息 和文件
 */
public class Recorder {

    //定义变量，记录击毁敌人坦克的数量
    private static int allEnemyTankNum=0;
    //定义IO对象,准备写数据到文件中
    private static BufferedWriter bwr=null;
    private static BufferedReader brr=null;

    private static String filePath="src/myRecoder.txt";

    //定义一个Node的Vector，用于保存敌人坦克的信息
    private static Vector<Node> nodes=new Vector<>();

    //定义Vector ，指向MyPanel 对象的 敌人坦克vector
    private static Vector<EnemyTank> enemyTanks=null;


    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    //该方法用于读取文件信息，从而用来恢复信息
    public static Vector<Node> getNodesAndEnemyTankRec() {

        try {
            brr=new BufferedReader(new FileReader(filePath));
            allEnemyTankNum=Integer.parseInt(brr.readLine());
            String line="";
            //循环读取
            while ((line=brr.readLine()) !=null){
                String[] xyd= line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);        //放入到vector中
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (brr!=null){
                try {
                    brr.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodes;
    }


    /// 增加一个方法，当游戏退出时，将allEnemyTankNum保存到myRecoder.txt文件中
    /// 保存敌人的坐标以及方向
    public static void saveRecord()  {
        try {
            bwr= new BufferedWriter(new FileWriter(filePath));
            //定义一个属性，去获得set方法 ，敌人坦克的vector
            for (int i = 0; i < enemyTanks.size(); i++) {
                //取出坦克
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive){
                    //保存enemyTank信息
                    String record=enemyTank.getX()+" "+ enemyTank.getY()+" "+enemyTank.getDirect();
                    //将保存的信息写入到文件
                    bwr.write(record+"\r\n");
                }
            }
            bwr.write(String.valueOf(allEnemyTankNum));
            bwr.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bwr!=null) {
                try {
                    bwr.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int  allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //当我方坦克击毁一个敌人坦克，就应当 allEnemyTankNum++
    public static void allEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }
}
