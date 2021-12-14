package com.party.minigame.impl;

import com.badlogic.gdx.Gdx;
import com.party.entity.Player;
import com.party.input.InputKeys;
import com.party.minigame.Minigame;

public class SpamMinigame extends Minigame {

    public SpamMinigame() {

    }

    @Override
    public void onKeyPress() {
        for (InputKeys value : InputKeys.values()) {
            if (Gdx.input.isKeyJustPressed(value.getKey())) {
                if(players.size() < value.getInputSender().getId()) continue;
                Player getWhoProvidedInput = players.get(value.getInputSender().getId());
                if (getWhoProvidedInput == null) return;
                getWhoProvidedInput.setPoints(getWhoProvidedInput.getPoints() + 1);
                System.out.println(getWhoProvidedInput + ": has " + getWhoProvidedInput.getPoints() + " points");
            }
        }

    }


}
