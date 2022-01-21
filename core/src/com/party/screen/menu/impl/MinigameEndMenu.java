package com.party.screen.menu.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.party.Game;
import com.party.minigame.Minigame;
import com.party.screen.menu.Menu;

public class MinigameEndMenu extends Menu {

    private Minigame game;

    public MinigameEndMenu(Minigame b) {
        game = b;
        setBackground(new Texture(Gdx.files.internal("menu/end.png")));
    }


    @Override
    public void render(Batch batch) {
        batch.draw(background, 0, 0);

        if (game.getWinner() == game.players.get(0)) {
            batch.draw(new Texture(Gdx.files.internal("first_player_hd.png")), 240, 365);
            batch.draw(new Texture(Gdx.files.internal("second_player_hd.png")), 240, 201);
        } else {
            batch.draw(new Texture(Gdx.files.internal("second_player_hd.png")), 240, 365);
            batch.draw(new Texture(Gdx.files.internal("first_player_hd.png")), 240, 201);


        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            Game.i().currentMenu = null;
            Sound b = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));
            b.play();
        }
    }
}
