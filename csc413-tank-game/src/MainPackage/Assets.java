/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.imageio.ImageIO.read;

/**
 *
 * @author Canran
 */
public class Assets {

    // public static HashMap<String, BufferedImage> imgs;
    public static BufferedImage t1, background, wall, bwall, menuBg, bullet, triplePU, explosion, hpu;
    public static SoundLoader bulletFire, oof, bgm;

    public void init() {
        try {
            //img
            background = read(getClass().getResource("/resources/Background.bmp"));
            t1 = read(getClass().getResource("/resources/tank1.png"));
            wall = read(getClass().getResourceAsStream("/resources/Wall1.gif"));
            bwall = read(getClass().getResource("/resources/Wall2.gif"));
            menuBg = read(getClass().getResource("/resources/menu.png"));
            bullet = read(getClass().getResource("/resources/Shell.png"));
            hpu = read(getClass().getResource("/resources/hpu.jpg"));
            triplePU = read(getClass().getResource("/resources/pu.png"));
            //sound
            bulletFire = new SoundLoader("/resources/Explosion_small.wav");
            oof = new SoundLoader("/resources/oof.wav");
            bgm = new SoundLoader("/resources/Music.mid");
//            background = read(new File("resources\\background.bmp"));
//            t1 = read(new File("resources\\tank1.png"));
//            wall = read(new File("resources\\Wall1.gif"));
//            bwall = read(new File("resources\\Wall2.gif"));
//            menuBg = read(new File("resources\\menu.png"));
//            bullet = read(new File("resources\\Shell.png"));
//            hpu = read(new File("resources\\hpu.jpg"));
//            triplePU = read(new File("resources\\pu.png"));
        } catch (IOException ex) {
            Logger.getLogger(Assets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
