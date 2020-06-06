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
public enum GameModel {
    INSTANCE;
    Tank myTank = new Tank(200, 500, Dir.UP, Group.GOOD);
    ColliderChain chain = new ColliderChain();
    private List<GameObject> gameObjects = new ArrayList<>();

    public static GameModel getInstance(){return INSTANCE;}

    private GameModel() {
        int initializationCount = PropertiesMgr.getInstance().getInt("initializationCount");
        //敌方坦克初始化
        for (int i = 0; i < initializationCount; i++) {
            gameObjects.add(new Tank(new Random().nextInt(500)+ i*80,100,Dir.DOWN, Group.BAD));
        }
        add(new Wall(150,150,200,50));
        add(new Wall(550,150,200,50));
        add(new Wall(300,300,50,200));
        add(new Wall(550,300,50,200));
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
                chain.collide(o1, o2);
            }
        }
    }
   /* public Tank getMainTank() {
        return myTank;
    }*/
}
