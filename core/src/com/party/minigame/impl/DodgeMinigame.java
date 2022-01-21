package com.party.minigame.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.party.Game;
import com.party.entity.Player;
import com.party.input.InputKeys;
import com.party.minigame.Minigame;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.HashSet;

public class DodgeMinigame extends Minigame {

    long startTime = -999999;
    private final HashSet<Boulder> boulders = new HashSet<>();
    private final HashSet<GamePlayer> gamePlayers = new HashSet<>();
    Texture boulderTexture = new Texture("minigame/boulder.png");
    Texture img = new Texture(Gdx.files.internal("minigame/dodge.png"));

    public DodgeMinigame() {

    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
        boulders.add(new Boulder());
        int index = 0;
        for (Player player : players) {
            index++;
            GamePlayer gp = new GamePlayer(player);
            gp.x = index * 100;
            gp.texture = new Texture(Gdx.files.internal("minigame/player_" + index + "_clear.png"));
            gamePlayers.add(gp);

        }
    }

    @Override
    public void onKeyPress() {
        if (System.currentTimeMillis() - startTime > 999999999) {
            Game.i().setMinigame(null);
            stop();
        }
        for (InputKeys value : InputKeys.values()) {
            if (Gdx.input.isKeyJustPressed(value.getKey())) {
                if (players.size() < value.getInputSender().getId()) continue;
                Player getWhoProvidedInput = players.get(value.getInputSender().getId());
                if (getWhoProvidedInput == null) return;
                GamePlayer gp = fromPlayer(getWhoProvidedInput);
                assert gp != null;
                gp.jump();


            }
        }
    }

    private GamePlayer fromPlayer(Player player) {
        for (GamePlayer gamePlayer : gamePlayers) {
            if (gamePlayer.player == player) return gamePlayer;
        }
        return null;
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(img, 0, 0);
        for (GamePlayer gamePlayer : gamePlayers) {
            gamePlayer.move();
            batch.draw(gamePlayer.texture, gamePlayer.x, gamePlayer.y + 100);

        }
        for (Boulder boulder : boulders) {
            boulder.x = boulder.x - boulder.speed;
            batch.draw(boulderTexture, boulder.x, boulder.y);
            for (GamePlayer gamePlayer : gamePlayers) {
                Point point = new Point(gamePlayer.x, gamePlayer.y + 100);
                Rectangle rectangle = new Rectangle(boulder.x - 20, boulder.y, 92, 92);
                if (rectangle.contains(point)) {
                    gamePlayer.player.setPoints(-999);
                    Game.i().setMinigame(null);
                    stop();
                }
            }
            if (boulder.x < -100) {
                boulders.remove(boulder);
                Boulder newBoulder = new Boulder();
                newBoulder.speed = randomSpeed();
                boulders.add(newBoulder);
            }

        }
    }


    public int randomSpeed() {
        int max = 8;
        int min = 5;
        int range = max - min + 1;
        int roll = (int) (Math.random() * range) + min;
        Sound dice_sound = Gdx.audio.newSound(Gdx.files.internal("sounds/jump.mp3"));
        dice_sound.play();
        return roll;
    }

    @Override
    public String getName() {
        return "Dodge";
    }

    @Override
    public String getDescription() {
        return "Dodge the incoming boulders, by jumping over them!";
    }

    @Override
    public String getInputPlayer1Desc() {
        return "Jump";
    }

    @Override
    public String getInputPlayer2() {
        return "P";

    }

    @Override
    public String getInputPlayer1() {
        return "E";

    }

    static class Boulder {
        public int x = 640;
        public int speed = 3;
        public int y = 40;
    }

    static class GamePlayer {
        public Player player;

        public GamePlayer(Player player) {
            this.player = player;
        }

        public Texture texture = null;

        public int x = 0;
        public int y = 0;
        public boolean hasJumped = false;
        public int maxJump = 175;


        public void jump() {
            if (y > 0) return;
            if (hasJumped) return;
            hasJumped = true;
        }

        public void move() {

            if (hasJumped && y < maxJump) {
                y = y + 6;
            }
            if (hasJumped && y >= maxJump) {
                hasJumped = false;
            }
            if (!hasJumped && y > 0) {
                y = y - 4;
            }
        }
    }
}
