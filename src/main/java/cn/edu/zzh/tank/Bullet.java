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
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private static final int SPEED = PropertiesMgr.getInstance().getInt("bulletSpeed");
    private boolean living = true;
    private TankFrame tankFrame;
    private Group group = Group.BAD;
    private Rectangle rectangle = new Rectangle();

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;

        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;

        tankFrame.bulletList.add(this);
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
        //update rectangle
        rectangle.x = this.x;
        rectangle.y = this.y;
        if (x < 0 || y < 0 || x > tankFrame.GAME_WIDTH || y > tankFrame.GAME_HEIGHT) living = false;
    }
    //敌我子弹相互抵消
    public void collidewithBullet(Bullet bullet) {
        if (this.group == bullet.getGroup()) return;
        if (rectangle.intersects(bullet.rectangle)){
            bullet.die();
            this.die();
        }
    }

    public void collidewithTank(Tank tank) {
        if (this.group == tank.getGroup()) return;
        if (rectangle.intersects(tank.rectangle)){
            tank.die();
            this.die();
            tankFrame.exploadList.add(new Expload(tank.getX(), tank.getY(),tankFrame ));
        }
    }

    private void die() {
        this.living = false;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Group getGroup() {
        return group;
    }
}
