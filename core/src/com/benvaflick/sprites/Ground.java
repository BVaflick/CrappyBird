package com.benvaflick.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ground {

    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    private Rectangle groundBounds1, groundBounds2;
    public static final int GROUND_VERTICAL_OFFSET = -40;

    public Ground(float x) {
        ground = new Texture("sprites/ground.png");
        groundPos1 = new Vector2(x, GROUND_VERTICAL_OFFSET);
        groundPos2 = new Vector2(x + ground.getWidth(), GROUND_VERTICAL_OFFSET);
        groundBounds1 = new Rectangle(groundPos1.x, groundPos1.y, ground.getWidth(), ground.getHeight());
        groundBounds2 = new Rectangle(groundPos2.x, groundPos2.y, ground.getWidth(), ground.getHeight());
    }

    public Texture getTexture() {
        return ground;
    }

    public Vector2 getGroundPos1() {
        return groundPos1;
    }

    public Vector2 getGroundPos2() {
        return groundPos2;
    }

    public void dispose() {
        ground.dispose();
    }
}
