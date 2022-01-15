package com.party.minigame.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.party.Game;
import com.party.entity.Player;
import com.party.input.InputKeys;
import com.party.minigame.Minigame;

public class SpamMinigame extends Minigame {

    long startTime = -999999;
    Texture img = new Texture(Gdx.files.internal("minigame/spammer.png"));
    public SpamMinigame() {

    }

    @Override
    public void start(){
        startTime = System.currentTimeMillis();
    }
    @Override
    public void onKeyPress() {
        if(System.currentTimeMillis() - startTime > 5000) {
            System.out.println("Winner: " + getWinner().getName() + " Points: " + getWinner().getPoints());
            Game.i().setMinigame(null);
            stop();
        }
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

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(img,0 ,0);
        Game.i().textRenderer.drawMessage(batch, "Player 1: " + players.get(0).getPoints(), 10, 30);
        Game.i().textRenderer.drawMessage(batch, "Player 2: " + players.get(1).getPoints(), 400, 30);
    }

    @Override
    public String getName(){
        return "Spammerz";
    }

    @Override
    public String getDescription(){
        return "Spam the buttons,as fast as you can";
    }

    @Override
    public String getInputPlayer2(){
        return "P";

    }
    @Override
    public String getInputPlayer1(){
        return "E";
    }
    @Override
    public String getInputPlayer1Desc(){
        return "Press Button";
    }
}
