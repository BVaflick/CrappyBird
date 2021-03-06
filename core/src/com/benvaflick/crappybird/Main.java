package com.benvaflick.crappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.benvaflick.state.GameStateManager;
import com.benvaflick.state.MenuState;

public class Main extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Crappy Bird";

	private GameStateManager stateManager;
	private SpriteBatch batch;
	private Music music;

	@Override
	public void create () {
		batch = new SpriteBatch();
		stateManager = new GameStateManager();
		Gdx.gl.glClearColor(1, 1, 1, 1);
		stateManager.push(new MenuState(stateManager));
//		music = Gdx.audio.newMusic(Gdx.files.internal("sound/music.mp3"));
//		music.setLooping(true);
//		music.setVolume(0.1f);
//		music.play();
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateManager.update(Gdx.graphics.getDeltaTime());
		stateManager.render(batch);
	}

	@Override
	public void dispose() {
		super.dispose();
//		music.dispose();
	}
}
