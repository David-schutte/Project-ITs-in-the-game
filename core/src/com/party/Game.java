package com.party;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class Game extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    TiledMap tileMap;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    @Override
    public void create() {
        tileMap = new TmxMapLoader().load("TestTom.tmx");

        float w = 1600;
        float h = 960;
        System.out.println(w + ": " + h);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();


        batch = new SpriteBatch();
       // img = new Texture("badlogic.jpg");
        renderer = new OrthogonalTiledMapRenderer(tileMap);
        renderer.render();

    }

    @Override
    public void render() {
        Gdx.graphics.getGL20().glClearColor( 1, 1, 1, 1 );
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        camera.update();
        renderer.setView(camera);
        renderer.render();

       // renderer.getSpriteBatch().begin();
      //  player.draw(renderer.getSpriteBatch());
       // renderer.getSpriteBatch().end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
