package com.party.minigame.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.party.Game;
import com.party.entity.Player;
import com.party.input.InputKeys;
import com.party.minigame.Minigame;

import java.util.ArrayList;

public class DobbelMinigame extends Minigame {

    long startTime = -999999;
    private boolean hasStarted = false;
    Texture img = new Texture(Gdx.files.internal("minigame/dobbel.png"));
    Texture dice = new Texture(Gdx.files.internal("minigame/dice_empty.png"));
    private final ArrayList<GamePlayer> gamePlayers = new ArrayList<>();

    public DobbelMinigame() {

    }

    public int diceRoll() {
        int max = 6;
        int min = 1;
        int range = max - min + 1;
        int roll = (int) (Math.random() * range) + min;
        Sound dice_sound = Gdx.audio.newSound(Gdx.files.internal("sounds/dice_roll.mp3"));
        dice_sound.play();
        return roll;
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        for (Player player : players) {
            GamePlayer gp = new GamePlayer(player);
            gamePlayers.add(gp);
        }
    }

    @Override
    public void onKeyPress() {
        if (System.currentTimeMillis() - startTime > 20000) {
            System.out.println("Winner: " + getWinner().getName() + " Points: " + getWinner().getPoints());
            Game.i().setMinigame(null);
            stop();
        }
        for (InputKeys value : InputKeys.values()) {
            if (Gdx.input.isKeyJustPressed(value.getKey())) {
                if (players.size() < value.getInputSender().getId()) continue;
                Player getWhoProvidedInput = players.get(value.getInputSender().getId());
                if (getWhoProvidedInput == null) return;
                GamePlayer gamePlayer = fromPlayer(getWhoProvidedInput);
                if (gamePlayer.diceOne == 0) {
                    gamePlayer.diceOne = diceRoll();
                    return;
                }
                if (gamePlayer.diceTwo == 0) {
                    gamePlayer.diceTwo = diceRoll();
                    return;
                }
            }
        }
    }

    long finishTimer = -999999;

    @Override
    public void render(SpriteBatch batch) {

        batch.draw(img, 0, 0);
        GamePlayer gamePlayer1 = gamePlayers.get(0);
        if (gamePlayer1.diceOne != 0) {
            batch.draw(dice, 65, 230);
            Game.i().textRenderer.drawCenteredMessage(batch, "" + gamePlayer1.diceOne, 65 + 50, 230 + 50);
        }
        if (gamePlayer1.diceTwo != 0) {
            batch.draw(dice, 170, 230);
            Game.i().textRenderer.drawCenteredMessage(batch, "" + gamePlayer1.diceTwo, 170 + 50, 230 + 50);
        }
        GamePlayer gamePlayer2 = gamePlayers.get(1);
        if (gamePlayer2.diceOne != 0) {
            batch.draw(dice, 367, 230);
            Game.i().textRenderer.drawCenteredMessage(batch, "" + gamePlayer2.diceOne, 367 + 50, 230 + 50);
        }
        if (gamePlayer2.diceTwo != 0) {
            batch.draw(dice, 472, 230);
            Game.i().textRenderer.drawCenteredMessage(batch, "" + gamePlayer2.diceTwo, 472 + 50, 230 + 50);
        }
        if (gamePlayer1.diceTwo != 0 && gamePlayer2.diceOne != 0) {
            if (finishTimer < 0) finishTimer = System.currentTimeMillis();
        }
        // System.out.println(finishTimer);
        if (finishTimer > 0 && System.currentTimeMillis() - finishTimer > 3000) {
            int scoreOne = gamePlayer1.diceOne + gamePlayer1.diceTwo;
            int scoreTwo = gamePlayer2.diceOne + gamePlayer2.diceTwo;
            if (scoreOne > scoreTwo) {
                gamePlayer1.player.setPoints(1);
            } else if (scoreOne < scoreTwo) {
                gamePlayer2.player.setPoints(1);
            }

            Game.i().setMinigame(null);
            stop();
        }

    }

    @Override
    public String getName() {
        return "Reaction Game";
    }

    @Override
    public String getDescription() {
        return "Press your button when,you see the image";
    }

    @Override
    public String getInputPlayer2() {
        return "P";

    }

    @Override
    public String getInputPlayer1() {
        return "E";
    }

    @Override
    public String getInputPlayer1Desc() {
        return "Press Button";
    }

    private GamePlayer fromPlayer(Player player) {
        for (GamePlayer gamePlayer : gamePlayers) {
            if (gamePlayer.player == player) return gamePlayer;
        }
        return null;
    }

    class GamePlayer {
        public Player player;
        public int diceOne;
        public int diceTwo;

        public GamePlayer(Player player) {
            this.player = player;
        }
    }
}
