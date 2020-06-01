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
        while (true){
            Thread.sleep(50);
            tankFrame.repaint();
        }
    }

}
