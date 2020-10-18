/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import GameObject.GameObject;
import GameObject.Tank;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author Canran
 */
public class Handler {

    ArrayList<Tank> tanks = new ArrayList<>();
    ArrayList<GameObject> obj = new ArrayList<>();

    public void tick() {
        for (int i = 0; i < obj.size(); i++) {
            obj.get(i).tick();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < obj.size(); i++) {
            obj.get(i).render(g);
        }
    }

    public void addTank(Tank tank) {
        this.tanks.add(tank);
    }

    public Tank getT(int i) {
        return tanks.get(i);
    }

    public ArrayList<Tank> getTanks() {
        return tanks;
    }

    public ArrayList<GameObject> getObjects() {
        return obj;
    }

    public void addObject(GameObject obj) {
        this.obj.add(obj);
    }

    public void remove(GameObject obj) {
        this.obj.remove(obj);
    }

    public void remove(int i) {
        this.obj.remove(i);
    }

    public GameObject get(int i) {
        return obj.get(i);
    }



}
