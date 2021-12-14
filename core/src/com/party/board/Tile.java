package com.party.board;

public class Tile {
    private int x;
    private int y;
    boolean special = false;
    boolean givesMoney = false;
    boolean removesMoney = false;
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
