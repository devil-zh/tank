package cn.edu.zzh.tank;

import java.awt.*;
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
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        g.fillRect(x, y, 50, 50);
        x += 50;
        y += 10;
    }
}
