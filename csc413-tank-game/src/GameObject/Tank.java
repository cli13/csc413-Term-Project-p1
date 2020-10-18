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
import java.awt.image.BufferedImage;

/**
 *
 * @author Canran
 */
public class Tank extends GameObject {

    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean ShootPressed;

    private final int ROTATIONSPEED = 3;

    private int life = 3;
    private int speed = 5;
    private int dmg = 20;
    private int maxHealth = 64;
    private int health = 64;
    private int angle;
    private BufferedImage img;
    private int shootDelay = 50;
    private int gunState = 1;
    private int timer;


    public Tank(int x, int y, ID id, Handler handler, BufferedImage img) {
        super(x, y, id, handler, img);

    }

    public Tank(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
        img = Assets.t1;
        if (this.getID() == ID.Tank2) {
            angle = 180;
        }
    }

    public void toggleUpPressed() {
        this.UpPressed = true;
    }

    public void toggleDownPressed() {
        this.DownPressed = true;
    }

    public void toggleRightPressed() {
        this.RightPressed = true;
    }

    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    public void toggleShootPressed() {
        this.ShootPressed = true;
    }

    public void unToggleUpPressed() {
        this.UpPressed = false;
    }

    public void unToggleDownPressed() {
        this.DownPressed = false;
    }

    public void unToggleRightPressed() {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    public void unToggleShootPressed() {
        this.ShootPressed = false;
    }

    //a bad decision hard coding respawn point to absolute pixel
    public void randomRespawn() {
        int random = (int) (Math.random() * 2);
        if (random % 2 == 0) {
            x = 128;
        } else {
            x = 23 * 64;
        }
        random = (int) (Math.random() * 2);
        if (random % 2 == 0) {
            y = 128;
        } else {
            y = 23 * 64;
        }
    }

    @Override
    public void tick() {
        if (health <= 0) {
            life--;
            health = maxHealth;
            gunState = 1;
            Assets.oof.play();
            randomRespawn();
        }
        if (shootDelay <= 50) {
            shootDelay++;
        }
        collision();
        if (this.UpPressed) {
            this.moveForwards();
        }
        if (this.DownPressed) {
            this.moveBackwards();
        }

        if (this.LeftPressed) {
            this.rotateLeft();
        }
        if (this.RightPressed) {
            this.rotateRight();
        }
        if (this.ShootPressed && shootDelay >= 50) {
            shoot();
            shootDelay = 0;
        }
    }

    private void rotateLeft() {
        this.angle -= this.ROTATIONSPEED;
    }

    private void rotateRight() {
        this.angle += this.ROTATIONSPEED;
    }

    private void moveBackwards() {
        velX = -(int) Math.round(speed * Math.cos(Math.toRadians(angle)));
        velY = -(int) Math.round(speed * Math.sin(Math.toRadians(angle)));
        x += velX;
        y += velY;
    }

    private void moveForwards() {
        velX = (int) Math.round(speed * Math.cos(Math.toRadians(angle)));
        velY = (int) Math.round(speed * Math.sin(Math.toRadians(angle)));
        x += velX;
        y += velY;
    }

    private void shoot() {
        if (gunState == 1) {
            handler.addObject(new Bullet(x + 24, y + 24, ID.Bullet, handler, this));
        }
        if (gunState == 2) {
            handler.addObject(new Bullet(x + 16, y + 16, ID.Bullet, handler, this));
            handler.addObject(new Bullet(x + 24, y + 24, ID.Bullet, handler, this));
            handler.addObject(new Bullet(x + 32, y + 32, ID.Bullet, handler, this));
        }
        timer = 0;
    }

    @Override
    public void render(Graphics g) {
        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), this.img.getWidth() / 2.0, this.img.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, rotation, null);
        g.setColor(Color.RED);
        g.fillRect(x, y - 8, maxHealth, 5); //hp display
        g.setColor(Color.GREEN);
        g.fillRect(x, y - 8, health, 5);
        if (this.getID() == ID.Tank2) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.BLUE);
        }
        for (int i = 0; i < life; i++) {//life display
            g.fillOval(x + (i * 15), y - 10 * 3, 16, 16);
        }
    }

    public int getAngle() {
        return angle;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 8, y + 8, 48, 48);
    }

    private void collision() {
        for (int i = 0; i < handler.getObjects().size(); i++) {
            GameObject temp = handler.get(i);
            if (getBounds().intersects(temp.getBounds())) {
                if (temp.getID() == ID.TripePU) {
                    this.gunState = 2;
                    handler.remove(temp);
                } else if (temp.getID() == ID.HealthPU) {
                    health += 40;
                    handler.remove(temp);
                } else if (temp.getID() == ID.Bullet) {
                    //nothing
                } else if (temp.getID() != this.getID()) {
                    x -= velX;
                    y -= velY;
                }
            }
        }
        if (timer <= 5) {
            timer++;
        }
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int newDmg) {
        dmg = newDmg;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLife() {
        return life;
    }

    public void setGunState(int i) {
        this.gunState = i;
    }
}
