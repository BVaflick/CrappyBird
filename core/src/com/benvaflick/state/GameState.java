package com.benvaflick.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.benvaflick.crappybird.Main;
import com.benvaflick.sprites.Bird;
import com.benvaflick.sprites.Ground;
import com.benvaflick.sprites.Tube;

public class GameState extends State {

    public static final int TUBE_SPACING = 125;
    public static final int TUBE_COUNT = 4;
    private Bird bird;
    private Ground ground;
    private Array<Tube> tubes;
    private Texture background;

    public GameState(GameStateManager manager) {
        super(manager);
        background = new Texture("bg.png");
        ground = new Ground(camera.position.x - camera.viewportWidth / 2);
        bird = new Bird(50, 300);
        camera.setToOrtho(false, Main.WIDTH / 2, Main.HEIGHT / 2);
        tubes = new Array<Tube>();
        for (int i = 0; i < TUBE_COUNT; i++) {
            tubes.add(new Tube((i * (TUBE_SPACING + Tube.TUBE_WIDTH)) + 400));
        }
    }

    @Override
    protected void handleInput() {
        if (bird.isAlive() && Gdx.input.justTouched()) {
            bird.jump();
        }

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        Gdx.gl.glClearColor(0.66f, 0.9f, 0.9f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background, camera.position.x - camera.viewportWidth / 2, 0);
        for (Tube tube : tubes) {
            spriteBatch.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            spriteBatch.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
        }
        spriteBatch.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y, bird.getWidth() / 2.0f, bird.getHeight() / 2.0f, bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
        spriteBatch.draw(bird.getPoo().getTexture(), bird.getPoo().getPosition().x, bird.getPoo().getPosition().y);
        spriteBatch.draw(ground.getTexture(), ground.getGroundPos1().x, ground.getGroundPos1().y);
        spriteBatch.draw(ground.getTexture(), ground.getGroundPos2().x, ground.getGroundPos2().y);
        spriteBatch.end();
    }

    @Override
    public void update(float delta) {
        handleInput();
        bird.update(delta);
        if(bird.isAlive()) {
            camera.position.x = bird.getPosition().x + 80;

        for (int i = 0; i < tubes.size; i++) {
            Tube tube = tubes.get(i);
            if (camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
                tube.reposition(tube.getPosTopTube().x + (Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
            }
            if (tube.collides(bird.getBirdBounds())) {
                bird.die();
            }
        }
        }
        if (bird.getPosition().y < 72) {
            if(bird.isAlive()) bird.die();
            stateManager.set(new GameoverState(stateManager));
        }
        updateGround();
        camera.update();
    }

    public void updateGround() {
        if((camera.position.x - camera.viewportWidth /2) > ground.getGroundPos1().x + ground.getTexture().getWidth()){
            ground.getGroundPos1().add(ground.getTexture().getWidth() * 2, 0);
        }
        if((camera.position.x - camera.viewportWidth /2) > ground.getGroundPos2().x + ground.getTexture().getWidth()){
            ground.getGroundPos2().add(ground.getTexture().getWidth() * 2, 0);
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
