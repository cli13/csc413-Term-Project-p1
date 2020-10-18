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
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 *
 * @author Canran
 */
public class GameOverState extends State {

    private GameStateManager manager;
    private int currentChoice = 0;
    private String[] options = {"PLAY AGAIN", "EXIT"};

    public GameOverState(GameStateManager manager) {
        this.manager = manager;
    }

    @Override
    public void init() {
    }

    @Override
    public void tick() {
    }

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        g.setColor(Color.white);
        Font font = new Font("Arial", Font.BOLD, 28);
        g.setFont(font);
        String msg = "GameOver, Thanks for playing";
        FontMetrics fm = g.getFontMetrics();
        int x = (Game.WIDTH - fm.stringWidth(msg)) / 2;
        int y = (fm.getAscent() + (Game.HEIGHT - (fm.getAscent() + fm.getDescent())) / 5);
        g.drawString(msg, x, y);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], (Game.WIDTH / 2) - 150, (Game.HEIGHT / 2) + i * 50);
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
