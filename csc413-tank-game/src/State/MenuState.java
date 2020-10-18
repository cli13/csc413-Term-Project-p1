/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package State;

import MainPackage.Assets;
import MainPackage.Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Canran
 */
public class MenuState extends State {

    private GameStateManager manager;
    private int currentChoice = 0;
    private String[] options = {"START", "EXIT"};

    private Font font;

    public MenuState(GameStateManager manager) {
        this.manager = manager;
        font = new Font("Arial", Font.BOLD, 28);
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics2D g) {
        g.drawImage(Assets.background, 0, 0, Game.WIDTH, Game.HEIGHT, null);
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.RED);
                g.drawRect((Game.WIDTH / 4) + i * 635, (Game.HEIGHT / 2) + 15, 100, 50);
            } else {
                g.setColor(Color.BLUE);
            }
            g.drawString(options[i], (Game.WIDTH / 4) + i * 650, (Game.HEIGHT / 2) + 50);
        }
    }

    private void select() {
        if (currentChoice == 0) {
            manager.setState(1);
        }
        if (currentChoice == 1) {
            System.exit(0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
