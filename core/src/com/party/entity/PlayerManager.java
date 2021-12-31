package com.party.entity;

import com.badlogic.gdx.graphics.Texture;
import com.party.Game;
import java.util.ArrayList;

public class PlayerManager {
    ArrayList<Player> players = new ArrayList<>();

    public PlayerManager() {

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * This method creates a player
     * @param game
     * @param playertexture
     * @return player
     */
    public Player createPlayer(Game game, Texture playertexture){
        Player player = new Player(game, playertexture);
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
