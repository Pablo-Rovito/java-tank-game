package main.entities;

import main.World;

import java.awt.*;

public abstract class Entity {
    public World w;
    public float x;
    public float y;
    public boolean delete = false;
    public Entity(float x, float y, World w) {
        this.w = w;
        this.x = x;
        this.y = y;
    }

    public abstract void update();
    public abstract void paint(Graphics2D g2d);

    public void remove() {
        delete = true;
    }
}
