package cn.edu.zzh.tank;

import java.awt.*;

/**
 * @author zzh
 * @date 2020-06-06 15:27
 * @description
 */
public class Wall extends GameObject{
    int w,h;

    public Rectangle rectangle;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rectangle = new Rectangle(x, y, w, h);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x,y,w,h);
        g.setColor(color);

    }
}
