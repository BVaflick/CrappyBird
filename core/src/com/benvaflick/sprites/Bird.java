package com.benvaflick.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Bird {

    private static final int GRAVITY = -15;
    private Vector3 velocity;
    private Vector3 position;
    private Rectangle birdBounds;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;
    private Sound dead;
    private Sound fall;
    private float rotation;
    private int width, height;
    private int pooCounter, randomJump;
    private Random random;
    private boolean isAlive;
    private Poo poo;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(100, 0, 0);
        texture = new Texture("sprites/birdanimation.png");
        width = texture.getWidth() / 3;
        height = texture.getHeight();
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.13f);
        birdBounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sound/sfx_wing.ogg"));
        dead = Gdx.audio.newSound(Gdx.files.internal("sound/dead.ogg"));
        fall = Gdx.audio.newSound(Gdx.files.internal("sound/fall.ogg"));
        isAlive = true;
        poo = new Poo(0,0);
        random = new Random();
        randomJump = random.nextInt(7) + 3;
    }

    public void update(float delta) {
        birdAnimation.update(delta, shouldntFlap());
        if (position.y > 0) velocity.add(0, GRAVITY, 0);
        position.add(velocity.x * delta, velocity.y * delta, 0);
        birdBounds.setPosition(position.x, position.y);
        if (position.y > 400) {
            position.y = 400;
            velocity.y = 0;
        }
        if (isFlyingUp()) {
            rotation += 600 * delta;
            if (rotation > 20) {
                rotation = 20;
            }
        }
        if (isFalling()) {
            rotation -= 480 * delta;
            if (rotation < -90) {
                rotation = -90;
            }
        }
        poo.update(delta);
    }

    public void jump() {
        velocity.y = 250;
        flap.play();
        pooCounter++;
        if (pooCounter == randomJump) {
            poo = new Poo(position.x, position.y);
            randomJump = random.nextInt(7) + 3;
            pooCounter = 0;
        }
    }

    public void die() {
        isAlive = false;
        dead.play();
        velocity.x = 0;
        jump();
        if (position.y > 72) {
            fall.play();
        }
    }

    public boolean isFalling() {
        return velocity.y < -190;
    }

    public boolean isFlyingUp() {
        return velocity.y > 0;
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
        poo.dispose();
    }

    public boolean shouldntFlap() {
        return velocity.y < 50 || !isAlive;
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getBird() {
        return birdAnimation.getFrame();
    }

    public Rectangle getBirdBounds() {
        return birdBounds;
    }

    public float getRotation() {
        return rotation;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Poo getPoo() {
        return poo;
    }
}
