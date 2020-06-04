package cn.edu.zzh.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zzh
 * @date 2020-05-31 21:32
 * @description
 */
public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 500, Dir.UP, Group.GOOD,this);
    List<Bullet> bulletList = new ArrayList<Bullet>();
    List<Tank> tankList = new ArrayList<Tank>();
    List<Expload> exploadList = new ArrayList<>();
    static final int GAME_WIDTH = PropertiesMgr.getInstance().getInt("GAME_WIDTH");
    static final int GAME_HEIGHT = PropertiesMgr.getInstance().getInt("GAME_HEIGHT");

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0,0,GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }


    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹个数："+bulletList.size(),10,60);
        g.drawString("敌人个数："+tankList.size(),10,80);
        g.setColor(color);
        if (myTank.isLiving()){
            myTank.paint(g);
        }else {
            g.setColor(Color.WHITE);
            g.drawString("GAME OVER",350,300);
            g.setColor(color);
        }

        //我方子弹
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }
        //敌方坦克
        for (int i = 0; i < tankList.size(); i++) {
            tankList.get(i).paint(g);
        }
        //子弹与坦克相撞
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < tankList.size(); j++){
                bulletList.get(i).collidewithTank(tankList.get(j));
                if (myTank.isLiving())
                    bulletList.get(i).collidewithTank(myTank);
            }
        }
        //子弹与子弹相互抵消
        for (int i = 0; i <bulletList.size()-1 ; i++) {
            for (int j = i+1; j < bulletList.size(); j++) {
                bulletList.get(i).collidewithBullet(bulletList.get(j));
            }
        }
        //敌我坦克相撞
        for (int i = 0; i < tankList.size(); i++) {
            myTank.collidewith(tankList.get(i));
        }
        //坦克爆炸
        for (int i = 0; i < exploadList.size(); i++) {
            exploadList.get(i).paint(g);
        }
    }

    class MyKeyListener extends KeyAdapter {
        boolean bA = false;
        boolean bD = false;
        boolean bW = false;
        boolean bS = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_A:
                    bA = true;
                    break;
                case KeyEvent.VK_D:
                    bD = true;
                    break;
                case KeyEvent.VK_W:
                    bW = true;
                    break;
                case KeyEvent.VK_S:
                    bS = true;
                    break;
                case KeyEvent.VK_J:
                    myTank.fire(FourDirFireStrategy.getInstance());
                    break;
            }
            setMainTankDir();
           // new Thread(()->new Audio("audio/tank_move.wav").play()).start();
        }
        //监听方向按键
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_A:
                    bA = false;
                    break;
                case KeyEvent.VK_D:
                    bD = false;
                    break;
                case KeyEvent.VK_W:
                    bW = false;
                    break;
                case KeyEvent.VK_S:
                    bS = false;
                    break;
            }
            setMainTankDir();
        }
        //设置方向
        private void setMainTankDir() {
            if (!bW && !bD && !bS && !bA) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (bA) {
                    myTank.setDir(Dir.LEFT);
                }

                if (bS) {
                    myTank.setDir(Dir.DOWN);
                }

                if (bD) {
                    myTank.setDir(Dir.RIGHT);
                }

                if (bW) {
                    myTank.setDir(Dir.UP);
                }
            }

        }

    }
}
