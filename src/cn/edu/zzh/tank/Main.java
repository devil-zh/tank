package cn.edu.zzh.tank;

import java.util.Random;

/**
 * @author zzh
 * @date 2020-05-31 20:58
 * @description tank main method
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tankFrame = new TankFrame();
        int initializationCount = PropertiesMgr.getInstance().getInt("initializationCount");
        //敌方坦克初始化
        for (int i = 0; i < initializationCount; i++) {
            tankFrame.tankList.add(new Tank(new Random().nextInt(500)+ i*80,100,Dir.DOWN, Group.BAD, tankFrame));
        }
        new Thread(()->new Audio("audio/war1.wav").loop()).start();
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }

}
