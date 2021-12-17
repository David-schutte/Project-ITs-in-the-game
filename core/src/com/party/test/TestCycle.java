package com.party.test;

import com.badlogic.gdx.math.Vector2;
import com.party.Game;
import com.party.board.Tile;
import com.party.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TestCycle {
    ArrayList<Vector2> locs = new ArrayList<>();
    Map<Integer, Vector2> locations = new HashMap<>();

    public TestCycle() {

        for (int i = 0; i < 20; i++) {
            locations.put(i, new Vector2(20 + (i * 25), 50));
        }
    }

    public void go() {


       // if(true) return;


        for (final Entity entity : Game.i().entities) {
            entity.setEndX( Game.i().getTileManager().getTileMap().get(0).getX());
            entity.setEndY( Game.i().getTileManager().getTileMap().get(0).getY());

            for (final Integer integer : Game.i().getTileManager().getTileMap().keySet()) {
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        System.out.println("moving...");
                        entity.setEndX(Game.i().getTileManager().getTileMap().get(integer).getX());
                        entity.setEndY(Game.i().getTileManager().getTileMap().get(integer).getY());
                    }
                };
                timer.schedule(timerTask, 3000L + integer * 2000L);
            }

        }
    }
}
