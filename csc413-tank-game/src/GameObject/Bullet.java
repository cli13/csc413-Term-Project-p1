/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import MainPackage.Assets;
import MainPackage.Handler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

/**
 *
 * @author Canran
 */
public class Bullet extends GameObject {

    private Tank tank;
    private int angle;
    private int speed;
    private int timer = 0;

    public Bullet(int x, int y, ID id, Handler handler, Tank tank) {
        super(x, y, id, handler);
        this.tank = tank;
        angle = tank.getAngle();
        speed = 15;
        Assets.bulletFire.play();
    }

    @Override
    public void tick() {
        if (timer > 5) {
            collision();
        }
        y += Math.round(speed * Math.sin(Math.toRadians(angle)));
        x += Math.round(speed * Math.cos(Math.toRadians(angle)));
        if (timer <= 5) {
            timer++;
        }
    }

    @Override
    public void render(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), 8, 8);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(Assets.bullet, rotation, null);

    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
    private int rand;

    private void collision() {
        for (int i = 0; i < handler.getObjects().size(); i++) {
            GameObject temp = handler.get(i);
            if (getBounds().intersects(temp.getBounds())) {
                if (temp.getID() == ID.Wall) {
                    handler.remove(this);
                } else if (temp.getID() == ID.BreakableWall) {
                    rand = (int) (Math.random() * 100);
                    handler.remove(i);
                    if (rand > 90) {
                        handler.addObject(new TripleBulletPU(x, y, ID.TripePU, handler));
                    }
                    if (rand < 10) {
                        handler.addObject(new HealthPU(x, y, ID.HealthPU, handler));
                    }
                    handler.remove(this);
                } else if (temp.getID() == ID.Tank) {
                    handler.getT(0).setHealth(handler.getT(0).getHealth() - handler.getT(0).getDmg());
                    handler.remove(this);
                } else if (temp.getID() == ID.Tank2) {
                    handler.getT(1).setHealth(handler.getT(1).getHealth() - handler.getT(1).getDmg());
                    handler.remove(this);
                }
            }
        }
    }
}
