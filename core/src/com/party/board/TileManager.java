package com.party.board;

import java.util.HashMap;
import java.util.Map;

public class TileManager {
    Map<Integer, Tile> tileMap = new HashMap<Integer, Tile>();
    public TileManager() {
    }

    public void load() {
//        addTile(new Tile(272,335));
//        addTile(new Tile(272,315));
//        addTile(new Tile(272,295));
//        addTile(new Tile(255,284));
//        addTile(new Tile(245,271));
//        addTile(new Tile(245,250));
//        addTile(new Tile(245,228));
//        addTile(new Tile(245,209));
//        addTile(new Tile(245,187));
//        addTile(new Tile(265,187));
//        addTile(new Tile(283,187));
//        addTile(new Tile(302,187));
//        addTile(new Tile(318,187));
//        addTile(new Tile(338,187));
//        addTile(new Tile(355,187));
//        addTile(new Tile(373,187));
//        addTile(new Tile(373,209));
//        addTile(new Tile(393,209));
//        addTile(new Tile(411,209));
//        addTile(new Tile(429,209));
//        addTile(new Tile(447,209));
//        addTile(new Tile(465,209));
//        addTile(new Tile(483,209));
//        addTile(new Tile(501,209));
//        addTile(new Tile(520,209));
//        addTile(new Tile(538,209));
//        addTile(new Tile(557,209));
//        addTile(new Tile(575,209));
//        addTile(new Tile(593,209));
//        addTile(new Tile(593,230));
        addTile(new Tile(593,251));
        addTile(new Tile(603,272));
        addTile(new Tile(620,272));
        addTile(new Tile(638,272));





    }

    private void addTile(Tile tile){
        this.tileMap.put(tileMap.size(), tile);
    }

    private void addTile(int index, Tile tile){
        this.tileMap.put(index, tile);
    }
    public Map<Integer, Tile> getTileMap() {
        return tileMap;
    }
}
