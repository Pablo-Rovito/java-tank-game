package main;

import java.awt.event.*;
import java.util.List;


public class ControllerManager extends MouseAdapter implements KeyListener {
    private World w;
    public Joystick joystick;
    public List<Character> keys = List.of('a', 's', 'd', 'w');
    public float xp, yp;
    public ControllerManager (World w) {
        this.w = w;

    }

    public void checkPlayer1() {
        if(joystick == null || (w.currentPlayer.score <= 0 && w.currentPlayer.life <= 0)) {
            joystick = new Joystick();
            w.login(joystick);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // not necessary
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Character key = e.getKeyChar();
        if(keys.contains(key)) {
            checkPlayer1();
            switch(key) {
                case 'a': joystick.left = true; break;
                case 'd': joystick.right = true; break;
                case 'w': joystick.up = true; break;
                case 's': joystick.down = true; break;
                default: break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Character key = e.getKeyChar();
        if(keys.contains(key)) {
            checkPlayer1();
            switch(key) {
                case 'a': joystick.left = false; break;
                case 'd': joystick.right = false; break;
                case 'w': joystick.up = false; break;
                case 's': joystick.down = false; break;
                default: break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        if(joystick != null) {
            joystick.fire = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mousePressed(e);
        joystick.fire = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if(joystick != null) {
            joystick.xp = e.getX();
            joystick.yp = e.getY();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        super.mouseMoved(e);
        if(joystick != null) {
            joystick.xp = e.getX();
            joystick.yp = e.getY();
        }
    }
}
