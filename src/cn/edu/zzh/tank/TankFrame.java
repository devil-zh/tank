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
public class TankFrame extends Frame{

    Tank myTank = new Tank(200,200, Dir.UP);

    public TankFrame(){
        setSize(800, 600);
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

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
    }

    class MyKeyListener extends KeyAdapter {
        boolean bA = false;
        boolean bD = false;
        boolean bW = false;
        boolean bS = false;
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
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
            switch (key){
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
            if (!bW && !bD && !bS && !bA){
                myTank.setMoving(false);
            }else {
                myTank.setMoving(true);
                if (bA) myTank.setDir(Dir.LEFT);
                if (bS) myTank.setDir(Dir.DOWN);
                if (bD) myTank.setDir(Dir.RIGHT);
                if (bW) myTank.setDir(Dir.UP);
            }

        }

    }
}
