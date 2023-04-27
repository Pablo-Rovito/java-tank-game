package main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameWindow
        extends JFrame implements Runnable {
    public int frame = 0;
    private boolean running = false;
    private BufferedImage buffer;
    private Graphics2D g2d;
    private World world;
    private ControllerManager manager;

    public GameWindow(String playerName) {
        super("Ventana");
        world = new World(playerName);
        manager = new ControllerManager(world);
        addKeyListener(manager);
        addMouseListener(manager);
        addMouseMotionListener(manager);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,500);
        buffer = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        g2d = (Graphics2D) buffer.getGraphics();
        new Thread(this).start();
        setVisible(true);
    }

    private void update() {
        frame++;
        world.update();
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        g2d.setColor(Color.black);
        g2d.fillRect(0,0,500,500);
        g2d.setColor(Color.green);
        g2d.drawString("frame: " + frame, 20, 60);
        world.render(g2d);
        g.drawImage(buffer, 0, 0, null);
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            update();
            try {
                Thread.sleep(30);
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
