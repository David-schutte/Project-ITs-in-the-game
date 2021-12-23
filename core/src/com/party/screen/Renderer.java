package com.party.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PixmapPacker;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.party.Game;
import com.party.entity.Player;

public class Renderer {

    Texture wallet;
    Texture star;

    BitmapFont font40;
    BitmapFont menuFont;

    public Renderer() {

        wallet = new Texture(Gdx.files.internal("wallet.png"));
        star = new Texture(Gdx.files.internal("koffie.png"));
        FreeTypeFontGenerator.setMaxTextureSize(4096);

        System.out.println(Gdx.files.internal("roboto.ttf").file().getAbsolutePath());
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("roboto.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.packer = new PixmapPacker(1024 , 1024 , Pixmap.Format.RGBA8888, 1, false);
        parameter.borderColor = Color.BLACK;
        parameter.shadowColor = new Color(0, 0, 0, 0.5f);
        parameter.color = Color.WHITE;

        // set font40 to generated font from ttf
        parameter.size = 20;
        parameter.borderWidth = parameter.size / 20;
        parameter.shadowOffsetX = parameter.size / 20;
        parameter.shadowOffsetY = parameter.size / 20;
        font40 = generator.generateFont(parameter);
        font40.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        font40.setUseIntegerPositions(false);

        // copy font40 to MENU font and set color (then set textbounds and establish a "button rectangle" with text centered)
        menuFont = new BitmapFont(font40.getData(), font40.getRegions(), true);
        menuFont.setColor(Color.TAN);
    }

    public void render(BitmapFont bitmap, Batch batch, Camera camera, String text) {

        // freetypeFontGenerator.generateFont(FreeTypeFontGenerator.FreeTypeFontParameterbitmap);
     //   batch.setProjectionMatrix(camera.combined);

        font40.setColor(Color.YELLOW);
       // font40.draw(batch, text, 10, camera.viewportHeight - 10);
        drawPlayerStatistics(batch, camera);
     //   batch.setProjectionMatrix(camera.view);
    }

    public void drawPlayerStatistics(Batch batch, Camera camera) {
        Player player1 = Game.i().getPlayerManager().getPlayers().get(0);
        Player player2 = Game.i().getPlayerManager().getPlayers().get(1);


        font40.draw(batch, "Speler 1", 10, 470);
        font40.draw(batch, "Speler 2", 550, 470);

        font40.draw(batch, "Geld: " + player1.getMoney(), 30, 445);
        font40.draw(batch, "Geld: " + player2.getMoney(), 560, 445);

        font40.draw(batch, "Koffie: " + player1.getStars(), 30, 420);
        font40.draw(batch, "Koffie: " + player2.getStars(), 550, 420);


        batch.draw(wallet,0, 425);
        batch.draw(wallet,530, 425);

        batch.draw(star,0, 400);
        batch.draw(star,520, 400);



    }

}
