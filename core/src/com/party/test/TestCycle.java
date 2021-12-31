package com.party.test;

import com.party.Game;
import com.party.entity.Entity;
import java.util.Timer;
import java.util.TimerTask;

public class TestCycle {

    public TestCycle() {

    }

    public void go(final int roll) {
        // if(true) return;

        for (final Entity entity : Game.i().entities) {
            System.out.println("You rolled:" + roll);
            entity.setEndX(Game.i().getTileManager().getTileMap().get(0).getX());
            entity.setEndY(Game.i().getTileManager().getTileMap().get(0).getY());
            final int currentX = (int) entity.getX(Game.i().getTileManager().getTileMap().get(0).getX());
            final int currentY = (int) entity.getX(Game.i().getTileManager().getTileMap().get(0).getY());

            entity.setEndX(Game.i().getTileManager().getTileMap().get(currentX + roll).getX());
            entity.setEndY(Game.i().getTileManager().getTileMap().get(currentY + roll).getY());

            for (final Integer integer : Game.i().getTileManager().getTileMap().keySet()) {
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        entity.setEndX(Game.i().getTileManager().getTileMap().get(currentX + roll).getX());
                        entity.setEndY(Game.i().getTileManager().getTileMap().get(currentY + roll).getY());
                        System.out.println(
                            "Current: " + integer + " Tile: " + Game.i().getTileManager().getTileMap().get(integer));
                    }
                };
                timer.schedule(timerTask, 2000L + integer * 1500L);
            }
        }
    }
}