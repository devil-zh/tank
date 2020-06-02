package cn.edu.zzh.tank;

import java.awt.*;

/**
 * @author zzh
 * @date 2020-06-01 11:16
 * @description
 */
public class Bullet {
    private int x, y;
    private Dir dir;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth(), HEIGHT = ResourceMgr.bulletD.getHeight();
    private static final int SPEED = 10;
    private boolean living = true;
    private TankFrame tankFrame;
    private Group group = Group.BAD;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        if (!living){
            tankFrame.bulletList.remove(this);
        }
        switch (dir){
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
        }
        move();
    }

    private void move() {
        switch (dir) {
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
        if (x < 0 || y < 0 || x > tankFrame.GAME_WIDTH || y > tankFrame.GAME_HEIGHT) living = false;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void collidewith(Tank tank) {
        if (this.group == tank.getGroup()) return;
        //todo: 只用一个Rectangle
        Rectangle rectangle1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(),Tank.WIDTH, Tank.HEIGHT);
        if (rectangle1.intersects(rectangle2)){
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.living = false;
    }
}
