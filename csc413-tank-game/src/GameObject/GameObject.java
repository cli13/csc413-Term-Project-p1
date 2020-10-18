/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import java.awt.Graphics;
import java.awt.Rectangle;
import MainPackage.Handler;
import java.awt.image.BufferedImage;

/**
 *
 * @author Canran
 */
public abstract class GameObject {

    public final static int DEFAULT_WIDTH = 64;
    public final static int DEFAULT_HEIGHT = 64;
    
    
    protected BufferedImage img; // sadly only use for tank all other objects are manually inserted in each class's draw
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected int velX = 0;
    protected int velY = 0;
    protected Handler handler;
    private ID id;

    public GameObject(int x, int y, ID id, Handler handler) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler = handler;
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
    }
    
    public GameObject(int x, int y, ID id, Handler handler, BufferedImage img) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.handler = handler;
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
        this.img = img;
    }

    public GameObject(int x, int y, int width, int height, ID id, Handler handler) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
        this.handler = handler;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public Rectangle getBounds(){
        return new Rectangle(x,y,width,height);
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the velX
     */
    public int getVelX() {
        return velX;
    }

    /**
     * @param velX the velX to set
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }

    /**
     * @return the velY
     */
    public int getVelY() {
        return velY;
    }

    /**
     * @param velY the velY to set
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }

    /**
     * @return the id
     */
    public ID getID() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setID(ID id) {
        this.id = id;
    }

}
