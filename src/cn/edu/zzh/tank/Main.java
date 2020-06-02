package cn.edu.zzh.tank;

/**
 * @author zzh
 * @date 2020-05-31 20:58
 * @description tank main method
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        //敌方坦克初始化
        for (int i = 0; i < 5; i++) {
            tankFrame.tankList.add(new Tank(i*150,100,Dir.DOWN, Group.BAD, tankFrame));
        }
        new Thread(()->new Audio("audio/war1.wav").loop()).start();
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }

}
