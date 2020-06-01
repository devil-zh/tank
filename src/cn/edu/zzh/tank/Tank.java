package cn.edu.zzh.tank;

import java.awt.*;

/**
 * @author zzh
 * @date 2020-06-01 10:27
 * @description 坦克实体类
 */
public class Tank {
    private int x = 200, y = 200;
    private Dir dir;
    private boolean moving =false;
    private static final int SPEED = 5;
    private TankFrame tankFrame;

    public Tank(int x, int y, Dir dir, TankFrame tankFrame){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, 50, 50);
        g.setColor(color);
        move();

    }

    private void move() {
        if (moving)
        switch (dir){
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
        }
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
        tankFrame.bulletList.add(new Bullet(this.x, this.y, this.dir, this.tankFrame));
    }
}
