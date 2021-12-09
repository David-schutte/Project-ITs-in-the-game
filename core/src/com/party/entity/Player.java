package com.party.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.party.Game;

public class Player extends Entity {

    private boolean focussed;

    private float speed = 2f;

    Texture playerTexture = new Texture(Gdx.files.internal("first_player.png"));

    public Player() {
        super();
        setTexture(playerTexture);
        setPosX(200);
        setPosY(100);

    }

    private Action runningAction;

    public void moveTo(Vector2 location) {
        runningAction = Actions.moveTo(location.x, location.y, speed);
        this.addAction(runningAction);
    }

    public void stopAction() {
        this.removeAction(runningAction);
    }

    public void stopAllActions() {
        this.clearActions();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(Game.i().getPlayerSprite(), getPosX(), getPosY());
    }

    public boolean isFocussed() {
        return focussed;
    }

    public void setFocussed(boolean focussed) {
        this.focussed = focussed;
    }

}
