package com.benvaflick.state;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 vector;
    protected GameStateManager stateManager;

    public State(GameStateManager manager) {
        this.stateManager = manager;
        camera = new OrthographicCamera();
        vector = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void render(SpriteBatch spriteBatch);
    public abstract void update(float delta);
    public abstract void dispose();
}
