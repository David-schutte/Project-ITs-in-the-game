package com.party.screen.menu.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.party.Game;
import com.party.screen.menu.Menu;

import java.awt.*;

public class PauseMenu extends Menu {

    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));
    private Rectangle resumeButton = null;
    private Rectangle exitButton = null;

    public PauseMenu() {
        sound.play();
        setBackground(new Texture(Gdx.files.internal("menu/pausemenu.png")));
        resumeButton = new Rectangle(85, 157, 277, 52);
        exitButton = new Rectangle(126, 367, 392, 56);

    }


    @Override
    public void render(Batch batch) {
        System.out.println(Gdx.input.getX() + " " + Gdx.input.getY());
        batch.draw(background, 0, 0);
        if (Gdx.input.isButtonPressed(0)) {
            Point cursor = new Point(Gdx.input.getX(), Gdx.input.getY());
            if (resumeButton.contains(cursor)) {
                Game.i().currentMenu = null;
                Sound b = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));
                b.play();
            }
            else if (exitButton.contains(cursor)) {
                Gdx.app.exit();
            }
        }
    }
}
