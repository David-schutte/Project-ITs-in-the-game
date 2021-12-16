package com.party;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.party.board.TileManager;
import com.party.entity.Entity;
import com.party.entity.Player;
import com.party.entity.PlayerManager;
import com.party.minigame.Minigame;
import com.party.test.TestCycle;

import java.util.HashSet;
import java.util.Iterator;


public class Game extends ApplicationAdapter {
    SpriteBatch batch;
    TiledMap tileMap;
    TiledMap dice;
    Minigame currentMinigame;
    TiledMapTileSet dicetiles;
    TileManager tileManager;
    TiledMapTile dice1;
    OrthogonalTiledMapRenderer renderer;
    PlayerManager playerManager = new PlayerManager();
    OrthographicCamera camera;
    Sprite playerSprite;
//    TiledMapTileLayer.Cell half1;
//    TiledMapTileLayer layer2;

    static Game game;
    private Player player;
    public HashSet<Entity> entities = new HashSet<>();


    @Override
    public void create() {
        System.out.println("\uD83D\uDC4B");

        game = this;
        tileManager = new TileManager();
        tileManager.load();
        player = playerManager.createPlayer();
        player.setFocussed(true);

        Player playertest = playerManager.createPlayer();
        tileMap = new TmxMapLoader().load("gameboard.tmx");
        MapLayer b = tileMap.getLayers().get(10);
        for (MapObject object : b.getObjects()) {
            System.out.println("Object: " + object.getProperties());
        }

        Iterator it = b.getProperties().getKeys();
        while(it.hasNext()){
            System.out.println("iterator: " + it.next());
        }
        dice = new TmxMapLoader().load("dice.tmx");
        System.out.println(dice.getTileSets().getTile(1));
        tileMap.getTileSets().getTileSet(1).putTile(3, dice.getTileSets().getTile(1));
        entities.add(player);
        entities.add(playertest);
//        System.out.println(tileMap.getTileSets().toString());
//        dicetileset = tileMap.getTileSets().getTileSet(0);
//        dice1 = dice.getTile(485);
//        half1.setTile(dice1);
//        layer2.setCell(5,5, half1);
//        System.out.println(dice1);
        float w = 1600;
        float h = 960;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);

      //  Viewport cameraViewport = new Viewport(camera.(), camera.getHeight);


        camera.setToOrtho(false, 1600, 960);
        //follow player with camera
        camera.position.set(player.getX(), player.getY(), 0);

        camera.update();


        batch = new SpriteBatch();
        renderer = new OrthogonalTiledMapRenderer(tileMap);
        renderer.render();

        new TestCycle().go();

    }


    @Override
    public void render() {
       System.out.println(Gdx.input.getX() + ", " + Gdx.input.getY());

        Gdx.graphics.getGL20().glClearColor(1, 1, 1, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

          if (playerManager.getFocussedPlayer() != null) {
                   camera.position.set(player.getPosX(), player.getPosY(), 0);
               }
        camera.update();


        renderer.setView(camera);
        renderer.render();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();


        if (currentMinigame != null) {
            currentMinigame.onKeyPress();
        }
        for (Entity entity : entities) {
            entity.render(batch);
            entity.onTick();
        }
        // if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {

        //     TiledMapTile b =tileMap.getTileSets().getTile(30);

        //     player.setX(Gdx.input.getX());
        //     player.setY(Gdx.input.getY());
        //     (new Vector2(Gdx.input.getX(), Gdx.input.getY()));
        //     System.out.println(player.getX() + " l " + Gdx.input.getX());

        //     player.draw(batch, 255f);
        // }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    public static Game i() {
        return game;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public Minigame getCurrentMinigame() {
        return currentMinigame;
    }

    public void setMinigame(Minigame minigame) {
        this.currentMinigame = minigame;
    }

    //draw a swastika
    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public TileManager getTileManager() {
        return tileManager;
    }
}
