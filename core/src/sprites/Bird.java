package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Bird {

    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;
    private Vector3 velocity;
    private Vector3 position;
    private Rectangle birdBounds;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;

    public Bird(int x, int y) {
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        birdBounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float delta) {
        birdAnimation.update(delta);
        if (position.y > 0) velocity.add(0, GRAVITY, 0);
        position.add(MOVEMENT * delta, velocity.y * delta, 0);
        if (position.y < 0) position.y = 0;
        birdBounds.setPosition(position.x, position.y);
    }

    public void jump() {
        velocity.y = 250;
        flap.play();
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
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
}
