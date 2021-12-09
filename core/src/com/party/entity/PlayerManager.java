package com.party.entity;

import java.util.HashSet;

public class PlayerManager {
    HashSet<Player> players = new HashSet<>();

    public HashSet<Player> getPlayers() {
        return players;
    }

    public Player createPlayer(){
        Player player = new Player();
        add(player);
        return player;
    }

    public void add(Player player) {
        players.add(player);
    }

    public void remove(Player player) {
        players.remove(player);
    }

    public PlayerManager() {

    }

    public Player getFocussedPlayer(){
        for(Player player : players){
            if(player.isFocussed()){
                return player;
            }
        }
        return null;
    }

}
