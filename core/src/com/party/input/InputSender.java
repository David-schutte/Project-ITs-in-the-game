package com.party.input;

public enum InputSender {
    Player1(0),
    Player2(1),
    Player3(2),
    Player4(3);

    private final int id;

    public int getId() {
        return id;

    }

    InputSender(int id) {
        this.id = id;
    }
}
