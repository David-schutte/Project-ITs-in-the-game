package com.party;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.party.board.TileManager;
import com.party.entity.Entity;
import com.party.entity.Player;
import com.party.entity.PlayerManager;
import com.party.minigame.Minigame;
import com.party.screen.Renderer;
import com.party.test.TestCycle;

import java.util.HashSet;
import java.util.Iterator;


public class Game extends ApplicationAdapter {
    SpriteBatch batch;
    TiledMap tileMap;
    TiledMap dice;
    Minigame currentMinigame;
    TileManager tileManager;
    OrthogonalTiledMapRenderer renderer;
    PlayerManager playerManager = new PlayerManager();
    BitmapFont font ;

    OrthographicCamera camera;
    Sprite playerSprite;
    Renderer textRenderer;

    static Game game;
    private Player player;
    public HashSet<Entity> entities = new HashSet<>();


    @Override
    public void create() {
        font = new BitmapFont();
        System.out.println(font);
        textRenderer = new Renderer();
        //  System.out.println("\uD83D\uDC4B");


        game = this;
        //  tileManager.load();
        player = playerManager.createPlayer();
        player.setFocussed(true);
        player.setMoney(0);

        Player playertest = playerManager.createPlayer();
        tileMap = new TmxMapLoader().load("gameboard.tmx");
        MapLayer b = tileMap.getLayers().get(10);
        for (MapObject object : b.getObjects()) {
            System.out.println("Object: " + object.getProperties());
        }

        Iterator it = b.getProperties().getKeys();
        while (it.hasNext()) {
            System.out.println("iterator: " + it.next());
        }
        dice = new TmxMapLoader().load("dice.tmx");
        System.out.println(dice.getTileSets().getTile(1));
        tileMap.getTileSets().getTileSet(1).putTile(3, dice.getTileSets().getTile(1));
        entities.add(player);

        entities.add(playertest);
        float w = 1600;
        float h = 960;
        diceRoll();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);

        //  Viewport cameraViewport = new Viewport(camera.(), camera.getHeight);


        tileManager = new TileManager();


        batch = new SpriteBatch();
        renderer = new OrthogonalTiledMapRenderer(tileMap);
        renderer.render();

          new TestCycle().go();

    }


    @Override
    public void render() {
        //  System.out.println(Gdx.input.getX() + ", " + Gdx.input.getY());

        Gdx.graphics.getGL20().glClearColor(1, 1, 1, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        camera.update();


        renderer.setView(camera);
        renderer.render();

        //   batch.setProjectionMatrix(camera.combined);
        batch.begin();

        if (currentMinigame != null) {
            currentMinigame.onKeyPress();
        }


        for (Entity entity : entities) {
            entity.render(batch);
            entity.onTick();
        }
        textRenderer.render(font, batch,camera,"ddddddddddddddddddddddddddddddddddddd!!");
        batch.end();

        if(Math.random() > 0.95){
            player.setMoney(player.getMoney() + 1);

        }
        if(Math.random() > 0.995){
            player.setStars(player.getStars() + 1);
        }
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

    /**
     * diceRoll gives a random number between 1 and 6 and makes the layers responsible for those numbers visible.
     */
    public void diceRoll() {
        int max = 6;
        int min = 1;
        int range = max - min + 1;
        int roll = (int)(Math.random() * range) + min;
        for (int i = 1; i<7; i++) {
            tileMap.getLayers().get(i+11).setVisible(i == roll);
        }
        System.out.println(roll);
    }

    public TiledMap getTileMap() {
        return tileMap;
    }
}
