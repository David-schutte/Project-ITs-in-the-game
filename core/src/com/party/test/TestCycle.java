package com.party.test;

import com.badlogic.gdx.math.Vector2;
import com.party.Game;
import com.party.entity.Entity;

import java.util.*;

public class TestCycle {
    ArrayList<Vector2> locs = new ArrayList<>();
    Map<Integer, Vector2> locations = new HashMap<>();
    public TestCycle(){

        for (int i = 0; i < 20; i++) {
            locations.put(i, new Vector2(20 + (i * 25), 50));
        }
    }
    public void go(){

        for (final Entity entity : Game.i().entities) {

            for (int i = 0; i < 20; i++) {

                Timer timer = new Timer();
                final int finalI = i;
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        entity.setX(locations.get(finalI).x);
                        entity.setY(locations.get(finalI).y);
                    }
                };
                timer.schedule(timerTask,i * 500);
            }
        }
    }
}
