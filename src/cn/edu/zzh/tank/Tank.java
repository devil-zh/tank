package cn.edu.zzh.tank;

import java.awt.*;
import java.util.Random;

/**
 * @author zzh
 * @date 2020-06-01 10:27
 * @description 坦克实体类
 */
public class Tank {
    private int x = 200, y = 200;
    private Dir dir = Dir.DOWN;
    private boolean moving =false;
    private static final int SPEED = 5;
    private TankFrame tankFrame;
    private boolean living = true;
    private Group group = Group.BAD;
    private Random random = new Random();
    public static final int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankD.getHeight();
    Rectangle rectangle = new Rectangle();

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;

        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
    }

    public boolean isLiving() {
        return living;
    }

    public void paint(Graphics g) {
        if (!living) tankFrame.tankList.remove(this);
        switch (dir){
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
        }

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
        //update rectangle
        rectangle.x = this.x;
        rectangle.y = this.y;

        if (random.nextInt(100)> 95 && this.group == Group.BAD) this.fire();
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            this.moving = true;
            randomDir();
        }
        bounksCheck();

    }

    private void bounksCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > tankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = tankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > tankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = tankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
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

    public Group getGroup() {
        return group;
    }

    public void setY(int y) {
        this.y = y;
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
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        tankFrame.bulletList.add(new Bullet(bX, bY, this.dir, this.group, this.tankFrame));
        if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }

    public void die() {
        this.living = false;
    }

    public void collidewith(Tank tank) {
        if (this.group == tank.getGroup()) return;
        if (rectangle.intersects(tank.rectangle)){
            tank.die();
            this.die();
            tankFrame.exploadList.add(new Expload(tank.getX(), tank.getY(),tankFrame ));
        }
    }
}
