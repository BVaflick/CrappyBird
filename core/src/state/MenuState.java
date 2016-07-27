package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.benvaflick.crappybird.Main;

public class MenuState extends State {

    private Texture background;
    private Texture playButton;

    public MenuState(GameStateManager manager) {
        super(manager);
        background = new Texture("bg.png");
        playButton = new Texture("playbtn.png");
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
        spriteBatch.draw(playButton, camera.position.x - (playButton.getWidth() / 2), camera.position.y);
        spriteBatch.end();
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        System.out.println("Menu screen disposed");
    }
}
