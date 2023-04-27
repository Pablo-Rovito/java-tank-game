package main;

import main.entities.Player;
import main.entities.Projectile;
import main.entities.Wall;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class World {
    public List<Player> players;
    public Player currentPlayer;
    public List<Projectile> projectiles;
    public List<Wall> walls;
    public String playerName;
    public World(String playerName) {
        this.playerName = playerName;
        players = new ArrayList<>();
        projectiles = new ArrayList<>();
        walls = new ArrayList<>();
    }
    public void update() {
        if(currentPlayer != null) currentPlayer.update();
        projectiles.forEach(Projectile::update);
        projectiles = projectiles.stream().filter(p -> !p.delete).collect(Collectors.toList());
    }
    public void paintMap(Graphics2D g2d) {
        walls.add(new Wall(10, 10, 10, 490, this));
        walls.add(new Wall(10, 490, 490, 490, this));
        walls.add(new Wall(490, 490, 490, 10, this));
        walls.add(new Wall(10, 33, 490, 33, this));
        walls.forEach(wall -> wall.paint(g2d));
    }
    public void render(Graphics2D g2d) {
        paintMap(g2d);
        g2d.setColor(Color.red);
        if (currentPlayer == null) {
            g2d.drawString("Tecleá A, S, D or W para empezar", 150, 250);
        } else if(currentPlayer.life > 0) {
            g2d.drawString("Puntos: " + currentPlayer.score, 20, 80);
            g2d.drawString("Vida: " + currentPlayer.life, 20, 110);
            currentPlayer.paint(g2d);
            projectiles.forEach(projectile -> projectile.paint(g2d));
        } else {
            g2d.drawString("L O S E R", 180, 150);
            g2d.drawString("Tecleá A, S, D or W para empezar", 150, 250);
        }
    }
    public void login(Joystick joystick) {
        currentPlayer = new Player(playerName, (float) Math.random() * 450, (float) Math.random() * 450, joystick, this);
        players.add(currentPlayer);
    }
}