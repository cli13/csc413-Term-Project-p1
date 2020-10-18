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
import java.awt.Rectangle;

/**
 *
 * @author Canran
 */
public class HealthPU extends GameObject {

    private Handler handler;

    public HealthPU(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }


    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        //g.fillRect(x, y, 32, 32);
        g.drawImage(Assets.hpu, x, y, 32, 32, null);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }



}
