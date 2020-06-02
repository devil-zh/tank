package cn.edu.zzh.tank;

import java.awt.*;

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
            tankFrame.tankList.add(new Tank(i*100,100,Dir.DOWN, Group.BAD, tankFrame));
        }

        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }

}
