package com.party.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Entity extends Actor {
    private float x;
    private float y;
    private Texture texture;

    public void render(Batch batch){

        batch.draw(texture, x, y);
    }



    public void setX(float x){this.x = x;}
    public void setY(float y){this.y = y;}
    public void setTexture(Texture texture){this.texture = texture;}

}
