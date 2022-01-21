package com.party.screen.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Menu {
    public Texture background;

    public Menu() {
    }

    public void render(Batch batch) {
        batch.draw(background, 0, 0);
    }

    public void setBackground(Texture background) {
        this.background = background;
    }

}
