package com.party.screen.menu.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.party.Game;
import com.party.minigame.Minigame;
import com.party.minigame.impl.SpamMinigame;
import com.party.screen.menu.Menu;

import java.awt.*;

public class MinigamePreviewMenu extends Menu {

    private Minigame game;
    private Rectangle button = null;

    public MinigamePreviewMenu(Minigame b) {
        game = b;
        setBackground(new Texture(Gdx.files.internal("menu/preview.png")));
        button = new Rectangle(212, 295, 210, 81);

    }


    @Override
    public void render(Batch batch) {
        batch.draw(background, 0, 0);

        Game.i().textRenderer.drawCenteredMessage(batch, game.getName(), 200, 400);
        Game.i().textRenderer.drawCenteredMessage(batch, game.getName(), 210, 320);
        String[] data = game.getDescription().split(",");
        int index = 0;
        for (String desc : data) {
            Game.i().textRenderer.drawCenteredMessage(batch, desc, 200, 140 - (index * 20));
            index++;
        }

          Game.i().textRenderer.drawCenteredMessage(batch, game.getInputPlayer1(), 600, 320);
          Game.i().textRenderer.drawCenteredMessage(batch, game.getInputPlayer2(), 600, 253);


        Game.i().textRenderer.drawMessage(batch, game.getInputPlayer1Desc(), 410, 320);
        Game.i().textRenderer.drawMessage(batch, game.getInputPlayer1Desc(), 410, 253);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {

            game.start();
            Game.i().setMinigame(game);
            Game.i().currentMenu = null;
            Sound b = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));
            b.play();
        }
    }
}
