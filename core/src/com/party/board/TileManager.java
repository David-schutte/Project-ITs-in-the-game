package com.party.board;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.party.Game;

import java.util.HashMap;
import java.util.Map;

public class TileManager {
    Map<Integer, Tile> tileMap = new HashMap<>();
    public TileManager() {
        //autoLoad();
        load();
    }

    public void load() {
        //beginpunt
        addTile(new Tile(63,264));
        addTile(new Tile(63,231));
        //nieuwe ruimte
        addTile(new Tile(63,200));
//        splitsing onderlangs
        addTile(new Tile(38,183));
        addTile(new Tile(24,167).setRemovesMoney(true));
        addTile(new Tile(24,137));
        addTile(new Tile(24,105));
        addTile(new Tile(24,72));
        addTile(new Tile(24,40));
        addTile(new Tile(50,40).setSpecial(true));
        addTile(new Tile(75,40));
        addTile(new Tile(101,40));
        addTile(new Tile(126,40).setRemovesMoney(true));
        addTile(new Tile(152,40));
        addTile(new Tile(178,40));
        addTile(new Tile(203,40));
        addTile(new Tile(203,72));
        //splitsing bovenlangs
//        addTile(103, new Tile(88,183));
//        addTile(104, new Tile(113,183));
//        addTile(105, new Tile(138,183));
//        addTile(106, new Tile(164,183));
//        addTile(107, new Tile(189,183));
//        addTile(108, new Tile(215,183));
//        addTile(109, new Tile(215,152));
//        addTile(110, new Tile(215,120));
//        addTile(111, new Tile(215,87));

        //einde splitsing
        addTile(new Tile(229,72));
        //nieuwe ruimte
        addTile(new Tile(254,72));
        addTile(new Tile(280,72));
        addTile(new Tile(305,72));
        //splitsing onderlangs
        addTile(new Tile(331,72));
        addTile(new Tile(357,72));
        addTile(new Tile(383,72));
        addTile(new Tile(408,72));
        addTile(new Tile(434,72));
        addTile(new Tile(459,72));
        addTile(new Tile(485,72));
        addTile(new Tile(510,72));
        addTile(new Tile(510,104));
        addTile(new Tile(510,136));
        //splitsing bovenlangs
//        addTile(121, new Tile(318,87));
//        addTile(122, new Tile(318,120));
//        addTile(123, new Tile(318,152));
//        addTile(124, new Tile(331,167));
//        addTile(125, new Tile(357,167));
//        addTile(126, new Tile(383,167));
//        addTile(127, new Tile(408,167));
//        addTile(128, new Tile(434,167));
//        addTile(129, new Tile(459,167));
//        addTile(130, new Tile(485,167));
        //einde splitsing
        addTile(new Tile(523,167));
        //nieuwe ruimte
        addTile(new Tile(549,167));
        addTile(new Tile(575, 167));
        addTile(new Tile(588, 183));
        addTile(new Tile(588, 216));
        addTile(new Tile(588, 249));
        addTile(new Tile(588, 280));
        addTile(new Tile(562, 280));
        //nieuwe ruimte
        addTile(new Tile(536, 280));
        addTile(new Tile(523, 296));
        addTile(new Tile(523, 328));
        addTile(new Tile(523, 360));
        addTile(new Tile(510, 376));
        addTile(new Tile(484, 376));
        addTile(new Tile(459, 376));
        addTile(new Tile(446, 360));
        addTile(new Tile(446, 328));
        addTile(new Tile(446, 296));
        addTile(new Tile(421, 263));
        addTile(new Tile(395, 263));
        addTile(new Tile(370, 263));
        addTile(new Tile(344, 263));
        addTile(new Tile(319, 296));
        addTile(new Tile(319, 296));
        addTile(new Tile(319, 328));
        addTile(new Tile(319, 360));
        addTile(new Tile(319, 392));
        addTile(new Tile(293, 392));
        addTile(new Tile(267, 392));
        addTile(new Tile(242, 392));
        //nieuwe ruimte
        addTile(new Tile(216, 392));
        addTile(new Tile(216, 423));
        addTile(new Tile(190, 423));
        addTile(new Tile(165, 423));
        addTile(new Tile(139, 423));
        addTile(new Tile(114, 423));
        addTile(new Tile(88, 423));
        addTile(new Tile(63, 423));
        addTile(new Tile(63, 392));
        addTile(new Tile(63, 359));
        addTile(new Tile(63, 327));
        addTile(new Tile(63, 296));
        //einde
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
