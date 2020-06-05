package cn.edu.zzh.tank;

import cn.edu.zzh.tank.collider.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author zzh
 * @date 2020-06-05 16:00
 * @description
 */
public class GameModel {
    Tank myTank = new Tank(200, 500, Dir.UP, Group.GOOD,this);
    ColliderChain chain = new ColliderChain();
    List<GameObject> gameObjects = new ArrayList<>();


    public GameModel() {
        int initializationCount = PropertiesMgr.getInstance().getInt("initializationCount");
        //敌方坦克初始化
        for (int i = 0; i < initializationCount; i++) {
            gameObjects.add(new Tank(new Random().nextInt(500)+ i*80,100,Dir.DOWN, Group.BAD, this));
        }
        gameObjects.add(myTank);
    }
    public void add(GameObject gameObject){
        this.gameObjects.add(gameObject);
    }

    public void remove (GameObject gameObject) {
        this.gameObjects.remove(gameObject);
    }
    public void paint(Graphics g) {

        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.setColor(color);
        if (myTank.isLiving()){
            myTank.paint(g);
        }else {
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER",350,300);
            g.setColor(color);
        }

        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }
        //互相碰撞
        for(int i=0; i<gameObjects.size(); i++) {
            for(int j=i+1; j<gameObjects.size(); j++) { //Comparator.compare(o1,o2)
                GameObject o1 = gameObjects.get(i);
                GameObject o2 = gameObjects.get(j);
                //for
                chain.collide(o1, o2, GameModel.this);
            }
        }

        /*
        //子弹与子弹相互抵消
        for (int i = 0; i <bulletList.size()-1 ; i++) {
            for (int j = i+1; j < bulletList.size(); j++) {
                bulletList.get(i).collidewithBullet(bulletList.get(j));
            }
        }
        //敌我坦克相撞
        for (int i = 0; i < tankList.size(); i++) {
            myTank.collidewith(tankList.get(i));
        }*/

    }
   /* public Tank getMainTank() {
        return myTank;
    }*/
}
