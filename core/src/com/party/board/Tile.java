package com.party.board;

public class Tile {
    private int x;
    private int y;
    boolean special = false;
    boolean givesMoney = false;
    boolean removesMoney = false;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isSpecial() {
        return special;
    }

    public boolean isGivesMoney() {
        return givesMoney;
    }

    public boolean isRemovesMoney() {
        return removesMoney;
    }

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Tile setSpecial(boolean special){
        this.special = special;
        return this;
    }
    public Tile setGivesMoney(boolean b){
        this.givesMoney = b;
        return this;
    }
    public Tile setRemovesMoney(boolean b){
        this.removesMoney = b;
        return this;
    }

}
