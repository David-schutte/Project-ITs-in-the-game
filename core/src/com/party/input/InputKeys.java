package com.party.input;

import com.badlogic.gdx.Input;

public enum InputKeys {
    INPUT_PLAYER_1(InputSender.Player1, Input.Keys.E),
    INPUT_PLAYER_2(InputSender.Player2, Input.Keys.P);


    private final int key;
    private final InputSender player;


    InputKeys(InputSender player, int p) {
        this.player = player;
        this.key = p;
    }

    public InputSender getInputSender() {
        return player;
    }

    public int getKey() {
        return key;
    }

}

