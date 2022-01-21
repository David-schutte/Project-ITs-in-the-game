package com.party.screen.menu.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.party.Game;
import com.party.screen.menu.Menu;

import java.awt.Point;
import java.awt.Rectangle;

public class PauseMenu extends Menu {

    Sound sound = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));
    private final Rectangle resumeButton;
    private final Rectangle exitButton;
    private final Rectangle restartButton;
    private final Rectangle optionsButton;

    public PauseMenu() {
        sound.play();
        setBackground(new Texture(Gdx.files.internal("menu/pausemenu.png")));
        resumeButton = new Rectangle(85, 157, 277, 52);
        exitButton = new Rectangle(126, 367, 392, 56 * 2);
        restartButton = new Rectangle(177, 229, 292, 51);
        optionsButton = new Rectangle(185, 300, 271, 48);
    }


    @Override
    public void render(Batch batch) {
        batch.draw(background, 0, 0);
        if (Gdx.input.isButtonPressed(0)) {
            Point cursor = new Point(Gdx.input.getX(), Gdx.input.getY());
            if (resumeButton.contains(cursor)) {
                Game.i().currentMenu = null;
                Sound b = Gdx.audio.newSound(Gdx.files.internal("sounds/click.mp3"));
                b.play();
            } else if (restartButton.contains(cursor)) {
                Game.i().restart();
            } else if (optionsButton.contains(cursor)) {
                Gdx.app.exit();
            } else if (exitButton.contains(cursor)) {
                Sound closing = Gdx.audio.newSound(Gdx.files.internal("sounds/exit_game.mp3"));
                closing.play();
                Gdx.app.exit();
            }
        }
    }
}
