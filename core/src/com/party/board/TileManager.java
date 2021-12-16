package com.party.board;

import java.util.HashMap;
import java.util.Map;

public class TileManager {
    Map<Integer, Tile> tileMap = new HashMap<Integer, Tile>();
    public TileManager() {
    }

    public void load() {
        addTile(new Tile(272,335));
        addTile(new Tile(272,310));
        addTile(new Tile(272,295));
        addTile(new Tile(272,270));

    }

    private void addTile(Tile tile){
        this.tileMap.put(tileMap.size(), tile);
    }

    public Map<Integer, Tile> getTileMap() {
        return tileMap;
    }
}
