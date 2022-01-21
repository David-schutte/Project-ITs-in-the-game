package com.party.board;

public class Tile {
    private final int x;
    private final int y;
    boolean special = false;
    boolean givesMoney = false;
    boolean removesMoney = false;
    boolean buyCoffee = false;
    boolean removesCoffee = false;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isBuyCoffee() {
        return buyCoffee;
    }

    public boolean isRemovesCoffee() {
        return removesCoffee;
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

    public Tile setRemovesCoffee(boolean removesCoffee) {
        this.removesCoffee = removesCoffee;
        return this;
    }

    public Tile setBuyCoffee(boolean buycoffee) {
        this.buyCoffee = buycoffee;
        return this;
    }

    public Tile setSpecial(boolean special) {
        this.special = special;
        return this;
    }

    public Tile setGivesMoney(boolean givemoney) {
        this.givesMoney = givemoney;
        return this;
    }

    public Tile setRemovesMoney(boolean removemoney) {
        this.removesMoney = removemoney;
        return this;
    }

}
