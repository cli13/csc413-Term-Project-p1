/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import State.GameStateManager;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyListener;

/**
 *
 * @author Canran
 */
public class Game extends JPanel implements Runnable, KeyListener {

    public final static String TITLE = "TANK GAME";
    public final static int WIDTH = 1280;
    public final static int HEIGHT = 720;

    public GameStateManager manager;
    private boolean running;
    private Thread thread;
    private JFrame jf;
    private Assets assets;

    public Game() {

    }

    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        init();
        //credit notch loop
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            repaint();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    private void init() {
        assets = new Assets();
        assets.init();
        setUpFrame();
        manager = new GameStateManager();
        this.addKeyListener(this);
    }

    private void setUpFrame() {
        jf = new JFrame(TITLE);
        jf.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        jf.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        jf.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        jf.add(this);
        jf.pack();
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    private void tick() {
        manager.tick();
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        manager.render(g2d);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        manager.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        manager.keyReleased(e);
    }

}
