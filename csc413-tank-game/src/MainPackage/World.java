/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MainPackage;

import GameObject.BreakableWall;
import GameObject.ID;
import GameObject.Wall;
import MainPackage.Handler;

/**
 *
 * @author Canran
 */
public class World {

    private int mapHeight;
    private int mapWidth;
    private Handler handler;

    public World(Handler handler) {
        this.handler = handler;
        loadMap();
    }

    private void loadMap() {
        mapHeight = 25;
        mapWidth = 25;
        int[][] map
                = { //           5              10             15             20             25
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//1
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 1},
                    {1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 2, 1, 0, 0, 0, 1, 2, 0, 0, 1},
                    {1, 0, 1, 0, 0, 2, 0, 1, 0, 0, 0, 1, 1, 0, 0, 2, 0, 1, 0, 1, 0, 2, 0, 0, 1},//5
                    {1, 0, 1, 0, 2, 0, 2, 1, 0, 0, 0, 1, 1, 0, 0, 2, 0, 0, 1, 0, 0, 2, 0, 0, 1},
                    {1, 0, 1, 1, 2, 0, 2, 1, 1, 0, 0, 1, 1, 0, 0, 2, 0, 1, 0, 1, 0, 2, 0, 0, 1},
                    {1, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1, 1, 0, 0, 2, 1, 0, 0, 0, 1, 2, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 2, 2, 2, 2, 2, 2, 2, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //10
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1},
                    {1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1, 1, 1, 1, 2, 2, 2, 2, 2, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 0, 2, 2, 0, 0, 0, 0, 2, 2, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, //15
                    {1, 0, 0, 1, 2, 2, 2, 2, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 2, 2, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
                    {1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 2, 2, 0, 1, 0, 0, 2, 0, 0, 1, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
                    {1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1}, //20
                    {1, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 2, 0, 0, 0, 1},
                    {1, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 1, 1, 0, 0, 0, 0, 2, 0, 2, 2, 2, 0, 0, 1},
                    {1, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 1},
                    {1, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},}; // 25

        for (int x = 0; x < map[0].length; x++) {
            for (int y = 0; y < map[x].length; y++) {
                if (map[x][y] == 1) {
                    handler.addObject(new Wall(y * 64, x * 64, ID.Wall, handler));
                } else if (map[x][y] == 2) {
                    handler.addObject(new BreakableWall(y * 64, x * 64, ID.BreakableWall, handler));
                }
            }
        }

    }

    /**
     * @return the mapHeight
     */
    public int getMapHeight() {
        return mapHeight;
    }

    /**
     * @return the mapWidth
     */
    public int getMapWidth() {
        return mapWidth;
    }

}
