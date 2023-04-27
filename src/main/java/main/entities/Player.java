package main.entities;

import main.Joystick;
import main.World;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static main.Utils.compareFloats;

public class Player extends Entity {
    public String name;
    public int SPEED = 10;
    public int MAGAZINE = 20;
    public int shots;
    public int life;
    public int score;
    public float dx, dy;
    public Joystick j;
    public boolean collision;
    public boolean reloading = false;
    public Player(String name, float x, float y, Joystick j, World w) {
        super(x, y, w);
        this.j = j;
        this.x = x;
        this.y = y;
        this.w = w;
        this.dx = (float) (-1 + Math.random() * 2);
        this.dy = (float) (-1 + Math.random() * 2);
        this.life = 100;
        this.collision = false;
        this.shots = 0;
        this.score = 3;
        this.name = name;
    }

    @Override
    public void paint(Graphics2D g2d) {
        drawStats(g2d);
        g2d.setColor(Color.yellow);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawOval((int) x - 10,(int) y - 10,20,20);
    }

    @Override
    public void update() {
        collision = false;
        checkFire();
        if(!j.connected) {
            remove();
        }

        dx = j.left ? -SPEED : j.right ? SPEED : 0;
        dy = j.up ? -SPEED : j.down ? SPEED : 0;

        w.walls.forEach(wall -> {
            if(compareFloats(x, wall.getX()) || compareFloats(x, wall.getXf())) {
                collision = true;
                life -= 10;
                if(life <= 0) {
                    respawn();
                } else {
                    if(dx > 0) {
                        x -= 50;
                    } else {
                        x += 50;
                    }
                    if(dy > 0) {
                        y = Math.min(y + dy, 450);
                    } else if(dy < 0) {
                        y = Math.max(y + dy, 50);
                    }
                }
            } else if(compareFloats(y, wall.getY()) || compareFloats(y, wall.getYf())) {
                collision = true;
                life -= 10;
                if(life <= 0) {
                    respawn();
                } else {
                    if(dy > 0) {
                        y -= 50;
                    } else {
                        y += 50;
                    }
                    if(dx > 0) {
                        x = Math.min(x + dx, 450);
                    } else if(dx < 0) {
                        x = Math.max(x + dx, 50);
                    }
                }
            }
        });
        if(!collision) {
            x += dx;
            y += dy;
        }
        if(x < 1) x = 15;
        if(x > 499) x = 485;
        if(y < 1) y = 15;
        if(y > 499) y = 485;
    }

    public void checkFire() {
        if(j.fire && !reloading) {
            shots++;
            if(shots >= MAGAZINE) {
                reloading = true;
                setReloading();
            } else {
                int quadrant = 1;
                if(j.yp < y && j.xp > x) quadrant = 2;
                if(j.yp > y && j.xp > x) quadrant = 3;
                if(j.yp > y && j.xp < x) quadrant = 4;
                double angle = Math.atan((j.yp - y)/(j.xp - x));
                w.projectiles.add(new Projectile(x, y, angle, quadrant, w));
            }
        }
    }
    public void drawStats(Graphics2D g2d) {
        g2d.setColor(Color.white);
        g2d.drawString(name, x - 15, y - 30);
        g2d.setColor(Color.red);
        g2d.drawLine((int) x - 10, (int) y - 20, (int) x + 10, (int) y - 20);
        g2d.setColor(Color.green);
        g2d.drawLine((int) x - 10, (int) y - 20, (int) (x - 10 + (double) life / 5), (int) y - 20);
        g2d.setColor(Color.yellow);
        if(reloading) {
            g2d.drawString("Recargando...", (int) x - 10, (int) y + 30);
        } else {
            g2d.drawString("Munición: " + (MAGAZINE - shots), (int) x - 10, (int) y + 30);
        }
    }

    public void setReloading() {
        Timer cooldown = new Timer();
        TimerTask reload = new TimerTask() {
            @Override
            public void run() {
                shots = 0;
                reloading = false;
            }
        };
        cooldown.schedule(reload, 2000);
    }

    public void respawn() {
        if(score <= 0) {
            delete = true;
        } else {
            this.x = (float) Math.random() * 450;
            this.y = (float) Math.random() * 450;
            this.life = 100;
            this.shots = 0;
            this.collision = false;
            this.score--;
        }
    }
}
