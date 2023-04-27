package main.entities;

import main.World;
import java.awt.*;

import static main.Utils.compareFloats;

public class Projectile extends Entity {
    private int SPEED = 50;
    public double angle;
    public int quadrant;
    public Color type;

    public Projectile(float x, float y, double angle, int quadrant, World w) {
        super(x, y, w);
        this.angle = angle;
        this.quadrant = quadrant;
        this.type = Color.red;
    }

    @Override
    public void update() {
        if(quadrant == 1 || quadrant == 4) {
            x -= SPEED * Math.cos(angle);
            y -= SPEED * Math.sin(angle);
        } else {
            x += SPEED * Math.cos(angle);
            y += SPEED * Math.sin(angle);
        }

        w.walls.forEach(wall -> {
            if (compareFloats(x, wall.getX())
                || compareFloats(x, wall.getXf())
                || compareFloats(y, wall.getY())
                || compareFloats(y, wall.getYf())) {
                remove();
            }
        });
    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.setColor(type);
        g2d.drawOval((int) x - 2,(int) y - 2,4,4);
    }
}
