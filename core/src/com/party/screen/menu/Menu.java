package com.party.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.awt.*;

public class Menu {
    public Texture background;
    public Menu(){
    }

    public void render(Batch batch) {
        batch.draw(background,0,0);
        // if(Gdx.input.getX())
    }

    public Texture getBackground(){
        return background;
    }
    public void setBackground(Texture background){
        this.background = background;
    }


}
