package com.party.board;

import java.util.HashMap;
import java.util.Map;

public class TileManager {
    Map<Integer, Tile> tileMap = new HashMap<Integer, Tile>();
    public TileManager() {
    }

    private void load() {
        addTile(new Tile(30,30));
        addTile(new Tile(40,30));
        addTile(new Tile(50,30));
        addTile(new Tile(60,30).);
    }

    private void addTile(Tile tile){
        this.tileMap.put(tileMap.size(), tile);
    }

}
