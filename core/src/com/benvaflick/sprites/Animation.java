package com.benvaflick.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;
    private boolean forward;

    public Animation(TextureRegion region, int frameCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for (int i = 0; i < frameCount; i++) {
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }

        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
        forward = true;
    }

    public void update(float delta, boolean shouldntFlap) {
        currentFrameTime += delta;
        if (currentFrameTime > maxFrameTime) {
            frame = forward ? frame + 1 : frame - 1;
            currentFrameTime = 0;
        }
        if (shouldntFlap) frame = 1;
        if (frame == frameCount-1) {
            forward = false;
        }
        else if (frame == 0) forward = true;
    }

    public TextureRegion getFrame() {
        return frames.get(frame);
    }
}
