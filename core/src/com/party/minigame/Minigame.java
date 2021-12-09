package com.party.minigame;

import com.party.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;

public class Minigame {
    public ArrayList<Player> players = new ArrayList<>();

    public Minigame() {

        //TODO: Implement this method
    }

    public void addPlayer(Player player) {
        player.setPoints(0);
        this.players.add(player);
    }

    public void start() {
    }

    public void stop() {

    }
    public void onKeyPress(){}
    public void setScore(int score) {

    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

    public void removeAllPlayers() {
        this.players.clear();
    }

    public void setPlayers(ArrayList<Player> players) {
        for (Player player : players) {
            player.setPoints(0);
        }
        this.players = players;
    }

    public Player getWinner() {
        Player winner = null;
        int highScore = 0;
        for (Player player : players) {
            if (player.getPoints() > highScore) {
                winner = player;
                highScore = player.getPoints();
            }
        }
        return winner;
    }

}
