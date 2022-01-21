package com.party.screen.menu.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import com.party.Game;
import com.party.screen.menu.Menu;

public class EndMenu extends Menu {


    private final Texture playerTexture;
    public EndMenu() {
        setBackground(new Texture(Gdx.files.internal("menu/gameEnd.png")));
        playerTexture = new Texture(Gdx.files.internal("minigame/player_" + winnerId() + "_clear.png"));
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
