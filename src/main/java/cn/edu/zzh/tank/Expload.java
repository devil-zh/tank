package cn.edu.zzh.tank;

import java.awt.*;

/**
 * @author zzh
 * @date 2020-06-01 11:16
 * @description
 */
public class Expload extends GameObject{
    private int x, y;
    public static final int WIDTH = ResourceMgr.exploads[0].getWidth();
    public static final int HEIGHT = ResourceMgr.exploads[0].getHeight();
    private boolean living = true;

    private int step = 0;

    public Expload(int x, int y) {
        this.x = x;
        this.y = y;
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        if (!living) GameModel.getInstance().remove(this);
       g.drawImage(ResourceMgr.exploads[step++],x, y, null);
       if (step >= ResourceMgr.exploads.length){
           step = 0;
           living = false;
       }
    }

    public boolean isLiving() {
        return living;
    }
}
