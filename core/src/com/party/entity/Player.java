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
    private int points;
    private int money;
    private int stars;

    public int getCurrent_tile_id() {
        return current_tile_id;
    }

    public void setCurrent_tile_id(int current_tile_id) {
        this.current_tile_id = current_tile_id;
        setEndX(Game.i().getTileManager().getTileMap().get(current_tile_id).getX());
        setEndY(Game.i().getTileManager().getTileMap().get(current_tile_id).getY());
    }

    private int current_tile_id = 0;

    private float speed = 2f;

    Texture playerTexture = new Texture(Gdx.files.internal("first_player.png"));



    public Player(Game game) {
        super();
        setTexture(playerTexture);

        // set the position we currently at, setPosx/y is a SCREEN position.
        // position is based on .getTileMap.get(0) which is the first in the tileset
        // collection
        setPosX(game.getTileManager().getTileMap().get(current_tile_id).getX());
        setPosY(game.getTileManager().getTileMap().get(current_tile_id).getY());

        // setEndX/y is the 'goal' position. The location we want to get to.
        setEndX(getPosX());
        setEndY(getPosY());
        focussed = false;
        money = 0;
        stars = 0;
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

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getStars() {
        return stars;
    }
}
