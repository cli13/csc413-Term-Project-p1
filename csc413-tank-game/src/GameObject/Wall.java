/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObject;

import MainPackage.Assets;
import MainPackage.Handler;
import java.awt.Graphics;

/**
 *
 * @author Canran
 */
public class Wall extends GameObject {
    
    public Wall(int x, int y, ID id, Handler handler) {
        super(x, y, id, handler);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.wall, x, y, width, height, null);
    }
}
