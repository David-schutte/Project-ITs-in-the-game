package com.party.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.party.Game;

public class Player extends Entity {

    private int points;
    private int money;
    private int coffee;

    public int getCurrent_tile_id() {
        return current_tile_id;
    }

    public void setCurrent_tile_id(int current_tile_id) {
        this.current_tile_id = current_tile_id;
        setEndX(Game.i().getTileManager().getTileMap().get(current_tile_id).getX());
        setEndY(Game.i().getTileManager().getTileMap().get(current_tile_id).getY());
    }

    private int current_tile_id = 0;

    public Player(Game game, Texture playerTexture) {
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
        money = 0;
        coffee = 0;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(Game.i().getPlayerSprite(), getPosX(), getPosY());
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

    public void setCoffee(int coffee) {
        this.coffee = coffee;
    }

    public int getCoffee() {
        return coffee;
    }

    @Override
    public int getEndX() {
        return Game.i().getTileManager().getTileMap().get(current_tile_id).getX();
    }

    @Override
    public int getEndY() {
        return Game.i().getTileManager().getTileMap().get(current_tile_id).getY();
    }
}
