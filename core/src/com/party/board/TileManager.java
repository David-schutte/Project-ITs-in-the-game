package com.party.board;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.party.Game;

import java.util.HashMap;
import java.util.Map;

public class TileManager {
    Map<Integer, Tile> tileMap = new HashMap<Integer, Tile>();
    public TileManager() {
        //autoLoad();
        load();
    }

    public void load() {
        addTile(new Tile(63,264));
        addTile(new Tile(63,264));
        addTile(new Tile(63,264));
        addTile(new Tile(63,231));
        addTile(new Tile(63,200));
        addTile(new Tile(38,183));
        addTile(new Tile(24,167));
        addTile(new Tile(24,137));
        addTile(new Tile(24,105));
        addTile(new Tile(24,72));
        addTile(new Tile(24,40));
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

    private void autoLoad(){

        TiledMap tileMap = Game.i().getTileMap();
        TiledMapTileLayer layer = ((TiledMapTileLayer)tileMap.getLayers().get(10));

        if(layer == null) {
            System.out.println("Layer is null");
            return;
        }

        //for each coorindate in the layer
        for(int x = 0; x < layer.getWidth(); x++){
            for(int y = 0; y < layer.getHeight(); y++){
                //get the tile
                if(layer.getCell(x,y) == null) continue;
                TiledMapTile object = layer.getCell(x,y).getTile();
                if(object != null){
                    //get the tile id
//                    int id = object.getProperties().get("id", Integer.class);
                    //get the tile

                    System.out.println(object);
                }
            }
        }

       for (MapObject object : layer.getObjects()) {
            System.out.println(object.getName());
        }
    }
}
