package com.party.screen.menu.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.party.Game;
import com.party.screen.menu.Menu;

import java.awt.*;

public class EndMenu extends Menu {

    private Rectangle button = null;
    Animation<TextureRegion> regionAnimation = null;


    private Texture playerTexture = null;
    public EndMenu() {
        setBackground(new Texture(Gdx.files.internal("menu/gameEnd.png")));
        playerTexture = new Texture(Gdx.files.internal("minigame/player_" + winnerId() + "_clear.png"));
        button = new Rectangle(212,295,210,81);



    }

    public int winnerId() {
        if(Game.i().getWinner() == Game.i().getPlayerManager().getPlayers().get(0)) {
            return 1;
        }
        return 2;
    }


    @Override
    public void render(Batch batch) {
        batch.draw(background, 0, 0);
        batch.draw(playerTexture, 300,207);
        Game.i().textRenderer.drawCenteredMessage(batch, "Player " + winnerId() + " wint!", 220, 238);
        if(Gdx.input.isButtonPressed(Input.Keys.ESCAPE) || Gdx.input.isButtonPressed(Input.Keys.ENTER))  {
            Gdx.app.exit();
        }
    }
}
