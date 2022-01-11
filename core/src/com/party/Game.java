package com.party;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.party.board.TileManager;
import com.party.entity.Entity;
import com.party.entity.Player;
import com.party.entity.PlayerManager;
import com.party.minigame.Minigame;
import com.party.minigame.impl.SpamMinigame;
import com.party.screen.Renderer;
import com.party.screen.menu.Menu;
import com.party.screen.menu.impl.PauseMenu;
import com.party.screen.menu.impl.StartMenu;

import java.util.ArrayList;
import java.util.Iterator;

public class Game extends ApplicationAdapter {
    SpriteBatch batch;
    TiledMap tileMap;
    Minigame currentMinigame;
    TileManager tileManager;
    OrthogonalTiledMapRenderer renderer;
    PlayerManager playerManager = new PlayerManager();
    BitmapFont font;
    public Menu currentMenu = null;
    OrthographicCamera camera;
    Sprite playerSprite;
    Renderer textRenderer;

    static Game game;
    private Player player1;
    private Player player2;
    private Player activeplayer;
    public ArrayList<Entity> entities = new ArrayList<>();
    int activeplayer_id = startingPlayer();
    int roll;
    int playernumber = activeplayer_id + 1;
    boolean moving = false;
    boolean turn_over = true;
    boolean n_is_pressed = false;

    @Override
    public void create() {
        font = new BitmapFont();
        System.out.println(font);
        textRenderer = new Renderer();
        game = this;
        tileMap = new TmxMapLoader().load("gameboard.tmx");
        MapLayer b = tileMap.getLayers().get(10);
        for (MapObject object : b.getObjects()) {
            System.out.println("Object: " + object.getProperties());
        }
        Iterator<String> it = b.getProperties().getKeys();
        while (it.hasNext()) {
            System.out.println("iterator: " + it.next());
        }

        float w = 1600;
        float h = 960;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);

        tileManager = new TileManager();

        player1 = playerManager.createPlayer(game, new Texture(Gdx.files.internal("first_player.png")));
        player2 = playerManager.createPlayer(game, new Texture(Gdx.files.internal("second_player.png")));
        entities.add(player1);
        entities.add(player2);
        activeplayer = (Player) entities.get(activeplayer_id);

        batch = new SpriteBatch();
        renderer = new OrthogonalTiledMapRenderer(tileMap);
        renderer.render();

        currentMenu = new StartMenu();
    }

    private void input() {
        if (currentMenu != null) {
            return;
        }
        boolean player_is_active = false;
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE) && currentMenu == null) {
            currentMenu = new PauseMenu();
            return;
        }
        if (!turn_over) {
//            System.out.println("Turn over false");
            if (Gdx.input.isKeyJustPressed(Input.Keys.J)) {
                activeplayer.setMoney(activeplayer.getMoney() - 20);
                activeplayer.setCoffee(activeplayer.getCoffee() + 1);
                turn_over = true;
            } else if (Gdx.input.isKeyJustPressed(Input.Keys.N)) {
                turn_over = true;
                n_is_pressed = true;
            }
        } else {
            for (Entity player : entities) {
                player_is_active = isPlayerActive((Player) player);
                if (player_is_active) {
                    break;
                }
            }
            if (!player_is_active) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                    roll = diceRoll();
                    int new_location = activeplayer.getCurrent_tile_id() + roll;
                    if (new_location > 71) {
                        new_location = new_location - 72;
                    }
                    activeplayer.setCurrent_tile_id(new_location);
                    moving = true;
                }
            }
        }
    }

    private boolean isPlayerActive(Player player) {
        return player.getEndX() != player.getPosX() || player.getEndY() != player.getPosY();
    }

    @Override
    public void render() {
        if (currentMenu != null) {
            batch.begin();
            currentMenu.render(batch);
            batch.end();
            return;

        }

        this.input();
        this.update();

        Gdx.graphics.getGL20().glClearColor(1, 1, 1, 1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.graphics.getGL20().glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        camera.update();

        renderer.setView(camera);
        renderer.render();

        batch.begin();

        if (currentMinigame != null) {
            currentMinigame.onKeyPress();
        }

        for (Entity entity : entities) {
            entity.render(batch);
            entity.onTick();
        }

        textRenderer.render(batch, roll, playernumber, turn_over);
        batch.end();
    }

    private void update() {
        if (currentMenu != null) {
            return;
        }

        if (moving) {


            if (!isPlayerActive(activeplayer)) {
                //last things during the turn
                if (game.getTileManager().getTileMap().get(activeplayer.getCurrent_tile_id()).isSpecial()) {
                    //TODO implement the special tiles
                    System.out.println("Special v2");
                    startRandomMinigame();
                    turn_over = true;
                } else if (game.getTileManager().getTileMap().get(activeplayer.getCurrent_tile_id()).isGivesMoney()) {
                    activeplayer.setMoney(activeplayer.getMoney() + 3);
                    turn_over = true;
                } else if (game.getTileManager().getTileMap().get(activeplayer.getCurrent_tile_id()).isRemovesMoney()) {
                    if (activeplayer.getMoney() > 3) {
                        activeplayer.setMoney(activeplayer.getMoney() - 3);
                    } else {
                        activeplayer.setMoney(0);
                    }
                    turn_over = true;
                } else if (game.getTileManager().getTileMap().get(activeplayer.getCurrent_tile_id()).isBuyCoffee()) {
                    turn_over = activeplayer.getMoney() < 20 || n_is_pressed;
                } else if (game.getTileManager().getTileMap().get(activeplayer.getCurrent_tile_id())
                    .isRemovesCoffee()) {
                    if (activeplayer.getCoffee() >= 1) {
                        activeplayer.setCoffee(activeplayer.getCoffee() - 1);
                    }
                    turn_over = true;
                }

                if (turn_over) {
                    activeplayer_id = (activeplayer_id + 1) % 2;
                    activeplayer = (Player) entities.get(activeplayer_id);
                    playernumber = activeplayer_id + 1;
                    n_is_pressed = false;
                    moving = false;
                }
            }
        }
    }

    private void startRandomMinigame() {
        SpamMinigame b = new SpamMinigame();
        b.addPlayer(player1);
        b.addPlayer(player2);
        b.start();
        currentMinigame = b;
    }

    @Override
    public void dispose() {
        batch.dispose();

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    public static Game i() {
        return game;
    }

    public Sprite getPlayerSprite() {
        return playerSprite;
    }

    public Minigame getCurrentMinigame() {
        return currentMinigame;
    }

    public void setMinigame(Minigame minigame) {
        this.currentMinigame = minigame;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    /**
     * diceRoll gives a random number between 1 and 6 and makes the layers responsible for those numbers visible.
     *
     * @return the number rolled
     */
    public int diceRoll() {
        int max = 6;
        int min = 1;
        int range = max - min + 1;
        int roll = (int) (Math.random() * range) + min;
        Sound dice_sound = Gdx.audio.newSound(Gdx.files.internal("sounds/dice_roll.mp3"));
        dice_sound.play();
        return roll;
    }

    public int startingPlayer() {
        int max = 1;
        int min = 0;
        int range = max - min + 1;
        int startingplayer = (int) (Math.random() * range) + min;
        System.out.println(startingplayer);
        return startingplayer;
    }

    public TiledMap getTileMap() {
        return tileMap;
    }
}
