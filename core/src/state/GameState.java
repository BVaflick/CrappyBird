package state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.benvaflick.crappybird.Main;
import sprites.Bird;
import sprites.Tube;

public class GameState extends State {

    public static final int TUBE_SPACING = 125;
    public static final int TUBE_COUNT = 4;
    public static final int GROUND_VERTICAL_OFFSET = -40;
    private Bird bird;
    private Array<Tube> tubes;
    private Texture background;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    public GameState(GameStateManager manager) {
        super(manager);
        background = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_VERTICAL_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2) + ground.getWidth(), GROUND_VERTICAL_OFFSET);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, Main.WIDTH / 2, Main.HEIGHT / 2);
        tubes = new Array<Tube>();
        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {
            bird.jump();
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - camera.viewportWidth / 2, 0);
        spriteBatch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        spriteBatch.draw(ground, groundPos1.x, groundPos1.y);
        spriteBatch.draw(ground, groundPos2.x, groundPos2.y);
        spriteBatch.end();
    }

    @Override
    public void update(float delta) {
        handleInput();
        bird.update(delta);
        camera.position.x = bird.getPosition().x + 80;
        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
            }
            if (tube.collides(bird.getBirdBounds())) {
                stateManager.set(new GameState(stateManager));
            }
        }
        updateGround();
        camera.update();
    }

    public void updateGround() {
        if((camera.position.x - camera.viewportWidth /2) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if((camera.position.x - camera.viewportWidth /2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        ground.dispose();
        for (Tube tube : tubes) {
            tube.dispose();
        }
        System.out.println("Game screen disposed");
    }
}
