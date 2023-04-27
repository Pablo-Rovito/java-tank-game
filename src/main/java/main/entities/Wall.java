package main.entities;

import main.World;

import java.awt.*;

public class Wall extends Entity {

    public float xf;
    public float yf;

    public Wall(float x, float y, float xf, float yf, World w) {
        super(x, y, w);
        this.x = x;
        this.y = y;
        this.xf = xf;
        this.yf = yf;
        this.w = w;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getXf() {
        return xf;
    }

    public float getYf() {
        return yf;
    }

    @Override
    public void update() {
        // no need for update yet, walls don't move or change
    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.setColor(Color.gray);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine((int) x, (int) y, (int) xf, (int) yf);
    }
}
