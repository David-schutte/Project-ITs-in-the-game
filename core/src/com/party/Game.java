package com.party;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.*;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.party.entity.Entity;
import com.party.entity.Player;
import com.party.entity.PlayerManager;
import com.party.minigame.Minigame;
import com.party.test.TestCycle;

import java.util.HashSet;


public class Game extends ApplicationAdapter {
    SpriteBatch batch;
    TiledMap tileMap;
    TiledMap dice;
    Minigame currentMinigame;
    OrthogonalTiledMapRenderer renderer;
    PlayerManager playerManager = new PlayerManager();
    OrthographicCamera camera;
    Sprite playerSprite;

    static Game game;
    private Player player;
    public HashSet<Entity> entities = new HashSet<>();


    @Override
    public void create() {
        System.out.println("\uD83D\uDC4B");

        game = this;
        player = playerManager.createPlayer();
        player.setFocussed(true);

        Player playertest = playerManager.createPlayer();
        tileMap = new TmxMapLoader().load("gameboardv2.tmx");
        dice = new TmxMapLoader().load("dice.tmx");
        tileMap.getTileSets().getTileSet(1).putTile(3, dice.getTileSets().getTile(1));
        entities.add(player);
        diceRoll();

        entities.add(playertest);
        float w = 1600;
        float h = 960;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);


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
        Gdx.graphics.getGL20().glClearColor(1, 1, 1, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        if (playerManager.getFocussedPlayer() != null) {
            camera.position.set(player.getPosX(), player.getPosY(), 0);
        }
        camera.update();


        renderer.setView(camera);
        renderer.render();

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

    public void diceRoll() {
        int max = 6;
        int min = 1;
        int range = max - min + 1;
        int roll = (int)(Math.random() * range) + min;
        for (int i = 1; i<7; i++) {
            if (i==roll) {
                tileMap.getLayers().get(i+1).setVisible(true);
            }
            else {
                tileMap.getLayers().get(i+1).setVisible(false);
            }
        }
        System.out.println(roll);
    }
}
