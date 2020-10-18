/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package State;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 *
 * @author Canran
 */
public class GameStateManager {

    private ArrayList<State> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int PLAYSTATE = 1;
    public static final int OVERSTATE = 2;

    public GameStateManager() {
        gameStates = new ArrayList<>();
        currentState = MENUSTATE;
        gameStates.add(new MenuState(this));
        gameStates.add(new PlayState(this));
        gameStates.add(new GameOverState(this));
    }

    public void setState(int state) {
        currentState = state;
        gameStates.get(currentState).init();
    }

    public int getState() {
        return currentState;
    }

    public void tick() {
        gameStates.get(currentState).tick();
    }

    public void render(Graphics2D g) {
        gameStates.get(currentState).render(g);
    }

    public void keyPressed(KeyEvent k) {
        gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(KeyEvent k) {
        gameStates.get(currentState).keyReleased(k);
    }
}
