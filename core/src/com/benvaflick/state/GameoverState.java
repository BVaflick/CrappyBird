package com.benvaflick.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.benvaflick.crappybird.Main;
import com.benvaflick.state.GameState;
import com.benvaflick.state.GameStateManager;
import com.benvaflick.state.State;

public class GameoverState extends State {

    private Texture background;
    private Texture gameover;

    public GameoverState(GameStateManager manager) {
        super(manager);
        background = new Texture("bg.png");
        gameover = new Texture("gameover.png");
        camera.setToOrtho(false, Main.WIDTH / 2, Main.HEIGHT / 2);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            stateManager.set(new GameState(stateManager));
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.draw(gameover, camera.position.x - (gameover.getWidth() / 2), camera.position.y);
        spriteBatch.end();
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameover.dispose();
        System.out.println("Gameover screen disposed");
    }
}
