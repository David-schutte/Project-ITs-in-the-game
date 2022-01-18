package com.party.minigame.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.party.Game;
import com.party.entity.Player;
import com.party.input.InputKeys;
import com.party.minigame.Minigame;

public class ReactionMinigame extends Minigame {

    long startTime = -999999;
    private boolean hasStarted = false;
    Texture img = new Texture(Gdx.files.internal("minigame/reaction1.png"));
    public ReactionMinigame() {

    }

    @Override
    public void start(){
        startTime = System.currentTimeMillis();
    }
    @Override
    public void onKeyPress() {
        if(System.currentTimeMillis() - startTime > 20000) {
            System.out.println("Winner: " + getWinner().getName() + " Points: " + getWinner().getPoints());
            Game.i().setMinigame(null);
            stop();
        }
        for (InputKeys value : InputKeys.values()) {
            if (Gdx.input.isKeyJustPressed(value.getKey())) {
                if(players.size() < value.getInputSender().getId()) continue;
                Player getWhoProvidedInput = players.get(value.getInputSender().getId());
                if (getWhoProvidedInput == null) return;
                if (hasStarted){
                    getWhoProvidedInput.setPoints(999);
                }
                else{
                    getWhoProvidedInput.setPoints(-999);

                }
                Game.i().setMinigame(null);
                stop();
            }
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(img,0 ,0);
        if(System.currentTimeMillis() - startTime > 5000 + Math.random() * 10000){

            hasStarted = true;
            img = new Texture(Gdx.files.internal("minigame/reaction2.png"));

        }
        if(hasStarted){
            Game.i().textRenderer.drawCenteredMessage(batch, "Druk op je input knop!", 320, 200);
        }
    }

    @Override
    public String getName(){
        return "Reaction Game";
    }

    @Override
    public String getDescription(){
        return "Press your button when,you see the image";
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
