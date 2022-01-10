package com.party.screen.menu.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.party.Game;
import com.party.screen.menu.Menu;

import java.awt.*;

public class StartMenu extends Menu {

    private Rectangle button = null;
    public StartMenu() {
        setBackground(new Texture(Gdx.files.internal("menu/mainmenu.png")));
        button = new Rectangle(212,295,210,81);

    }


    @Override
    public void render(Batch batch) {

        batch.draw(background, 0, 0);
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
