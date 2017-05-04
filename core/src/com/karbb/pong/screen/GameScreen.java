package com.karbb.pong.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.karbb.pong.World;

public class GameScreen extends AbstractsScreen {

	SpriteBatch batch;
	ShapeRenderer renderer;
	BitmapFont font;

	World world;

	
	public GameScreen(Game game) {
		super(game);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		world = new World();

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		world.update(delta);
		world.draw(batch, renderer);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
