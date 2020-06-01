package cn.edu.zzh.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author zzh
 * @date 2020-05-31 21:32
 * @description
 */
public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 200, Dir.UP);
    Bullet bullet = new Bullet(300, 300, Dir.DOWN);
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

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
        gOffScreen.setColor(Color.GRAY);
        gOffScreen.fillRect(0,0,GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }


    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        bullet.paint(g);
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
            }
            setMainTankDir();
        }

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
