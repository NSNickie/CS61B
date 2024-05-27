package knightworld;

import tileengine.TERenderer;
import tileengine.TETile;

import java.util.HashMap;

/**
 * Draws a world consisting of knight-move holes.
 */
public class KnightWorld {
    public HashMap<Integer, int[]> templateMap;

    private TETile[][] tiles;
    // TODO: Add additional instance variables here

    public KnightWorld(int width, int height, int holeSize) {
        // TODO: Fill in this constructor and class, adding helper methods and/or classes as necessary to draw the
        //  specified pattern of the given hole size for a window of size width x height. If you're stuck on how to
        //  begin, look at the provided demo code!
        this.tiles = new TETile[width][height];
        this.initTempateMap();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

            }
        }
    }

    public void initTempateMap() {
        int[][] arrays = {
                {0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0}
        };

        for (int i = 0; i < arrays.length; i++) {
            this.templateMap.put(i, arrays[i]);
        }
    }

    /**
     * Returns the tiles associated with this KnightWorld.
     */
    public TETile[][] getTiles() {
        return tiles;
    }

    public static void main(String[] args) {
        // Change these parameters as necessary
        int width = 50;
        int height = 30;
        int holeSize = 2;

        KnightWorld knightWorld = new KnightWorld(width, height, holeSize);

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(knightWorld.getTiles());

    }
}
