package cn.edu.zzh.tank;

import java.awt.*;
import java.util.Random;

/**
 * @author zzh
 * @date 2020-06-01 10:27
 * @description 坦克实体类
 */
public class Tank {
    private int x, y;
    private Dir dir = Dir.DOWN;
    private boolean moving =false;
    private static final int SPEED = PropertiesMgr.getInstance().getInt("tankSpeed");
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

    public void fire(FireStrategy fireStrategy) {
        fireStrategy.fire(this);
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

        if (random.nextInt(100)> 95 && this.group == Group.BAD) this.fire(DefaultFireStrategy.getInstance());
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            this.moving = true;
            randomDir();
        }
        //边界检测
        bounksCheck();

        //update rectangle
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    private void bounksCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
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

    public TankFrame getTankFrame() {
        return tankFrame;
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

    public boolean isLiving() {
        return living;
    }
}
