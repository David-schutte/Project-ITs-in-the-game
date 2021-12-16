package com.party.test;

import com.badlogic.gdx.math.Vector2;
import com.party.Game;
import com.party.entity.Entity;
import com.party.minigame.Minigame;
import com.party.minigame.impl.SpamMinigame;

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


        for (final Entity entity : Game.i().entities) {
            entity.setEndX(500);
            entity.setEndY(500);


            Timer timer = new Timer();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    entity.setEndX((int) locations.get(1).x);
                    entity.setEndY((int) locations.get(1).y);
                }
            };
            timer.schedule(timerTask, 10000L);
        }
        Timer timer = new Timer();
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                Minigame minigame = new SpamMinigame();
                minigame.setPlayers(Game.i().getPlayerManager().getPlayers());
                Game.i().setMinigame(minigame);
                System.out.println("MINIGAME INIT");
            }
        };
        timer.schedule(timerTask2, 10000L);
        TimerTask timerTask3 = new TimerTask() {
            @Override
            public void run() {
                Minigame minigame = Game.i().getCurrentMinigame();
                if (minigame == null) {
                    System.out.println("no minigame...");
                    return;
                }
                ;

                minigame.stop();
                System.out.println("Winner of the minigame: " + minigame.getWinner() + " with score: " + minigame.getWinner().getPoints());
                Game.i().setMinigame(null);
            }
        };
        timer.schedule(timerTask3, 17000L);
    }
}
