package com.party.minigame;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.party.Game;
import com.party.entity.Player;
import com.party.screen.menu.impl.MinigameEndMenu;

import java.util.ArrayList;

public class Minigame {
    public ArrayList<Player> players = new ArrayList<>();

    public Minigame() {
    }

    public void addPlayer(Player player) {
        player.setPoints(0);
        this.players.add(player);
    }

    public void start() {
    }

    public void stop() {
        getWinner().setMoney(getWinner().getMoney() + 20);
        Game.i().currentMenu = new MinigameEndMenu(this);

    }

    public void onKeyPress() {

    }

    public Player getWinner() {
        Player winner = null;
        int highScore = -999999;
        for (Player player : players) {
            if (player.getPoints() > highScore) {
                winner = player;
                highScore = player.getPoints();
            }
        }
        if (winner == null) {
            players.get(0);
        }
        return winner;
    }

    public void render(SpriteBatch batch) {
    }

    public String getName() {
        return "Example";
    }

    public String getDescription() {
        return "Example Desc";
    }

    public String getInputPlayer1() {
        return "E";
    }

    public String getInputPlayer2() {
        return "P";
    }

    public String getInputPlayer1Desc() {
        return "Default description!";
    }

}
