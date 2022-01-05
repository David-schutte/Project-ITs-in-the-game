package com.party.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Entity extends Actor {
    private int x;
    private int y;

    private int endX;
    private int endY;
    private double speed = 1;
    private Texture texture;


    public Entity() {

    }

    public void render(Batch batch) {

        batch.draw(texture, x, y);
    }

    public void setPosX(int x) {
        this.x = x;
    }

    public int getPosX() {
        return this.x;
    }

    public int getPosY() {
        return this.y;
    }

    public void setEndX(int x){this.endX = x;}
    public void setEndY(int y){this.endY = y;}

    public int getEndX() {
        return this.x;
    }

    public int getEndY() {
        return this.y;
    }

    public void setPosY(int y) {
        this.y = y;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void move() {
        if (x > endX) x -= speed;
        if (x < endX) x += speed;
        if (y > endY) y -= speed;
        if (y < endY) y += speed;
    }

    public void onTick() {
        if (x != endX || y != endY) {
            move();
        }
    }
}
