package com.party;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.party.entity.Entity;
import com.party.entity.Player;
import com.party.test.TestCycle;

import java.util.HashSet;


public class Game extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    TiledMap tileMap;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    Sprite playerSprite;
    static Game game;
    private Player player;
    public HashSet<Entity> entities = new HashSet<>();

    @Override
    public void create() {
        game = this;
        player = new Player();
        tileMap = new TmxMapLoader().load("gameboardv0.tmx");

        entities.add(player);

        float w = 1600;
        float h = 960;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
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

        camera.update();
        renderer.setView(camera);
        renderer.render();

        batch.begin();

        for (Entity entity : entities) {
            entity.render(batch);
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
        img.dispose();

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
}
