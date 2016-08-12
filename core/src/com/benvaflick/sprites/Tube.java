package com.benvaflick.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {

    public static final int FLUCTUATION = 130;
    public static final int TUBE_GAP = 100;
    public static final int TUBE_WIDTH = 52;
    public static final int LOWEST_OPENING = 120;

    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Rectangle topBounds, bottomBounds;
    private Random rand;

    public Tube(float x) {
        topTube = new Texture("sprites/toptube.png");
        bottomTube = new Texture("sprites/bottomtube.png");
        rand = new Random();

        posTopTube = new Vector2(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x, posTopTube.y - topTube.getHeight() - TUBE_GAP);
        topBounds = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        bottomBounds = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public void reposition(float x) {
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - topTube.getHeight() - TUBE_GAP);
        topBounds.setPosition(posTopTube.x, posTopTube.y);
        bottomBounds.setPosition(posBotTube.x, posBotTube.y);
    }

    public boolean collides(Rectangle birdRectangle) {
        return topBounds.overlaps(birdRectangle) || bottomBounds.overlaps(birdRectangle);
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
