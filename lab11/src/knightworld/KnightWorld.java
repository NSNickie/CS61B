package knightworld;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

import java.util.HashMap;

/**
 * Draws a world consisting of knight-move holes.
 */
public class KnightWorld {
    public HashMap<Integer, int[]> templateMap;

    private final TETile[][] tiles;
    // TODO: Add additional instance variables here

    public KnightWorld(int width, int height, int holeSize) {
        // TODO: Fill in this constructor and class, adding helper methods and/or classes as necessary to draw the
        //  specified pattern of the given hole size for a window of size width x height. If you're stuck on how to
        //  begin, look at the provided demo code!
        this.tiles = new TETile[width][height];
        this.initTemplateMap();
        int unitX = 0;
        int unitY = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                unitX = (x / holeSize) % 5;
                unitY = (y / holeSize) % 5;

                this.tiles[x][y] = (this.templateMap.get(unitX))[unitY] == 1 ? Tileset.NOTHING : Tileset.WALL;
            }
        }
    }

    public void initTemplateMap() {
        int[][] unitArray = {
                {0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 0, 0, 0, 1},
                {0, 1, 0, 0, 0}
        };
        this.templateMap = new HashMap<Integer, int[]>();
        for (int i = 0; i < unitArray.length; i++) {
            this.templateMap.put(i, unitArray[i]);
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
        int width = 60;
        int height = 45;
        int holeSize = 3;

        KnightWorld knightWorld = new KnightWorld(width, height, holeSize);

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(knightWorld.getTiles());

    }
}
