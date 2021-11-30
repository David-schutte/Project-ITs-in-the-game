package com.party;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import lombok.Getter;

@Getter
public class Game extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    TiledMap tileMap;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    Sprite playerSprite;
    static Game game;

    @Override
    public void create() {
        game = this;
        tileMap = new TmxMapLoader().load("TestTom.tmx");

        for (MapLayer layer : tileMap.getLayers()) {
            System.out.println(layer.getName() + ": " + layer.getObjects().getCount() );
            layer.setVisible(true);
        }

      //  System.out.println(Gdx.files.internal("nismo.png").file().getAbsolutePath());

       Texture texture = new Texture(Gdx.files.internal("nismo.png"));
        playerSprite = new Sprite(texture);


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

        batch.begin();
        playerSprite.draw(batch);
        batch.end();

        if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            playerSprite.setPosition(Gdx.input.getX() - playerSprite.getWidth()/2,
                    Gdx.graphics.getHeight() - Gdx.input.getY() - playerSprite.getHeight()/2);
        }

       // renderer.getSpriteBatch().begin();
      //  player.draw(renderer.getSpriteBatch());
       // renderer.getSpriteBatch().end();
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

    public static Game i() {return game;}
}
