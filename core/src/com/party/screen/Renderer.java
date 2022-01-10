package com.party.screen;

import com.badlogic.gdx.Gdx;
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
    Texture coffee;
    Texture diceEmpty;

    BitmapFont font40;
    BitmapFont menuFont;

    public Renderer() {
        wallet = new Texture(Gdx.files.internal("wallet.png"));
        coffee = new Texture(Gdx.files.internal("koffie.png"));
        diceEmpty = new Texture(Gdx.files.internal("dice_empty.png"));
        FreeTypeFontGenerator.setMaxTextureSize(4096);

        System.out.println(Gdx.files.internal("roboto.ttf").file().getAbsolutePath());
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("roboto.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.packer = new PixmapPacker(1024, 1024, Pixmap.Format.RGBA8888, 1, false);
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

    public void render(Batch batch, int roll, int playernumber, boolean turn_over) {
        font40.setColor(Color.YELLOW);
        drawPlayerStatistics(batch);
        drawAdditionalMessages(batch, playernumber, turn_over, roll);
    }

    private void drawAdditionalMessages(Batch batch, int playernumber, boolean turn_over, int roll) {
        font40.draw(batch, "Speler " + playernumber, 120, 285);
        font40.draw(batch, "mag gooien!", 120, 265);

        //only display roll if there was a roll, not on the initial screen
        if (roll!=0) {

            batch.draw(diceEmpty,250, 185);
            Color c = font40.getColor();
            font40.setColor(new Color(200,90,30,255));
            font40.draw(batch, "" + roll, 260, 210);
            font40.setColor(c);
        }
        if (!turn_over) {
            font40.draw(batch, "Wil je koffie kopen? j / n", 300, 200);

        }
    }

    public void drawPlayerStatistics(Batch batch) {
        Player player1 = Game.i().getPlayerManager().getPlayers().get(0);
        Player player2 = Game.i().getPlayerManager().getPlayers().get(1);

        font40.draw(batch, "Speler 1", 40, 470);
        font40.draw(batch, "Speler 2", 540, 470);

        font40.draw(batch, "Geld: " + player1.getMoney(), 40, 445);
        font40.draw(batch, "Geld: " + player2.getMoney(), 540, 445);

        font40.draw(batch, "Koffie: " + player1.getCoffee(), 40, 420);
        font40.draw(batch, "Koffie: " + player2.getCoffee(), 540, 420);

        batch.draw(wallet,10, 425);
        batch.draw(wallet,510, 425);

        batch.draw(coffee,10, 400);
        batch.draw(coffee,510, 400);

    }
}
