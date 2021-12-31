package com.party.input;

public enum InputSender {
    Player1(0),
    Player2(1);

    private final int id;

    public int getId() {
        return id;

    }

    InputSender(int id) {
        this.id = id;
    }
}
