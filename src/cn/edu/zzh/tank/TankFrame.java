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
    int x = 200, y = 200;

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
        g.fillRect(x, y, 50, 50);
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
                    x -= 10;
                    bA = true;
                    break;
                case KeyEvent.VK_D:
                    x += 10;
                    bD = true;
                    break;
                case KeyEvent.VK_W:
                    y -= 10;
                    bW = true;
                    break;
                case KeyEvent.VK_S:
                    y += 10;
                    bS = true;
                    break;
                default:
                    break;
            }
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
                default:
                    break;
            }
        }
    }
}
