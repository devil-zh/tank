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
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.black);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }


    @Override
    public void paint(Graphics g) {
        GameModel.getInstance().paint(g);

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
                    GameModel.getInstance().myTank.fire(FourDirFireStrategy.getInstance());
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
             Tank myTank = GameModel.getInstance().myTank;
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
