package com.party.entity;

import com.party.Game;
import java.util.ArrayList;

public class PlayerManager {
    ArrayList<Player> players = new ArrayList<>();

    public PlayerManager() {

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player createPlayer(Game game){
        Player player = new Player(game);
        add(player);
        return player;
    }

    public void add(Player player) {
        players.add(player);
    }

    public void remove(Player player) {
        players.remove(player);
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
