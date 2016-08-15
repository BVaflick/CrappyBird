package com.benvaflick.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Poo {

    private static final int GRAVITY = -15;
    private Vector2 position;
    private Vector2 velocity;
    private Texture currentTexture;
    private Texture drop;
    private Texture puddle;


    public Poo(float x, float y) {
        position = new Vector2(x, y);
        velocity = new Vector2(60, 0);
        drop = new Texture("sprites/poo.png");
        puddle = new Texture("sprites/puddle.png");
        currentTexture = drop;
    }

    public void update(float delta) {
        if (position.y > 0) velocity.add(0, GRAVITY);
        position.add(velocity.x * delta, velocity.y * delta);
        if (position.y < 70) {
            position.y = 70;
            currentTexture = puddle;
            velocity.x = 0;
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return currentTexture;
    }

    public void dispose() {
        currentTexture.dispose();
        drop.dispose();
        puddle.dispose();
    }
}
