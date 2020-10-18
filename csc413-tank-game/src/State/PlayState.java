/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package State;

import GameObject.ID;
import GameObject.Tank;
import MainPackage.World;
import MainPackage.Assets;
import MainPackage.Game;
import java.awt.Graphics2D;
import MainPackage.Handler;
import MainPackage.TankControl;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 *
 * @author Canran
 */
public class PlayState extends State {

    private Handler handler;
    private Tank t1;
    private Tank t2;
    private TankControl tc1;
    private TankControl tc2;
    private World world;
    public GameStateManager manager;

    public PlayState(GameStateManager manager) {
        this.manager = manager;
    }

    @Override
    public void init() {
        handler = new Handler();
        t1 = new Tank(128, 128, ID.Tank, handler);
        t2 = new Tank(64 * 23, 64 * 23, ID.Tank2, handler);
        world = new World(handler);
        handler.addObject(t1);
        handler.addObject(t2);
        handler.addTank(t1);
        handler.addTank(t2);
        tc1 = new TankControl(t2, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);
        tc2 = new TankControl(t1, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_ENTER);
        Assets.bgm.loop();
    }

    @Override
    public void tick() {
        handler.tick();
        if (t1 != null && t2 != null) {
            checkOutOfBound(t1);
            checkOutOfBound(t2);
            if (t1.getLife() == 0 || t2.getLife() == 0) {
                Assets.bgm.stop();
                manager.setState(2);
            }
        }
    }

    @Override
    public void render(Graphics2D g) {
        BufferedImage map = new BufferedImage(world.getMapWidth() * 64, world.getMapHeight() * 64, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = map.createGraphics();
        g2.drawImage(Assets.background, 0, 0, world.getMapWidth() * 64, world.getMapHeight() * 64, null);
        handler.render(g2);
        BufferedImage tw1 = map.getSubimage(moveX(t1), moveY(t1), Game.WIDTH / 2, Game.HEIGHT);
        BufferedImage tw2 = map.getSubimage(moveX(t2), moveY(t2), Game.WIDTH / 2, Game.HEIGHT);
        Image minimap = map.getScaledInstance(256, 256, Image.SCALE_FAST);
        g.drawImage(tw1, 0, 0, null);
        g.drawImage(tw2, Game.WIDTH / 2, 0, null);
        g.drawImage(minimap, (Game.WIDTH / 2) - 128, 0, null);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        tc1.keyPressed(e);
        tc2.keyPressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        tc1.keyReleased(e);
        tc2.keyReleased(e);
    }

    public int moveY(Tank t) {
        int camY = t.getY() + 32 - Game.HEIGHT / 2;
        int maxOffSetY = world.getMapHeight() * 64 - Game.HEIGHT;
        if (camY > maxOffSetY) {
            return maxOffSetY;
        } else if (camY < 0) {
            return 0;
        } else {
            return camY;
        }
    }

    public int moveX(Tank t) {
        int camX = t.getX() + 32 - Game.WIDTH / 4;
        int maxOffSetX = (world.getMapWidth() * 64 - Game.WIDTH / 2);
        if (camX > maxOffSetX) {
            return maxOffSetX;
        } else if (camX < 0) {
            return 0;
        } else {
            return camX;
        }
    }

    //to prevent tank from getting out of the map
    public void checkOutOfBound(Tank t) {
        int newPos = 64;
        if (t.getX() < 0) {
            t.setX(newPos);
        } else if (t.getX() > world.getMapWidth() * 64) {
            newPos = world.getMapWidth() * 64 - 128;
            t.setX(newPos);
        }
        if (t.getY() < 0) {
            t.setY(newPos);
        } else if (t.getY() > world.getMapHeight() * 64) {
            newPos = world.getMapHeight() * 64 - 128;
            t.setY(newPos);
        }
    }

}
