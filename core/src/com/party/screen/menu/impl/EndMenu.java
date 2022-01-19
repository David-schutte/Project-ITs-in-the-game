package com.party.screen.menu.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.party.Game;
import com.party.screen.menu.Menu;

import java.awt.*;

public class EndMenu extends Menu {

    private Rectangle button = null;
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
        batch.draw(playerTexture, 300,220);
        Game.i().textRenderer.drawCenteredMessage(batch, "Player " + winnerId() + " wins!", 220, 250);
        if(Gdx.input.isButtonPressed(0)) {
            Point cursor = new Point(Gdx.input.getX(), Gdx.input.getY());
            if (button.contains(cursor)) {
                Game.i().currentMenu = null;
                Sound b = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));
                b.play();
            }
        }
    }
}
