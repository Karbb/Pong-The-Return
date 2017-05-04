package com.karbb.pong.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TitleScreen extends AbstractsScreen {

	private SpriteBatch batch;
	private BitmapFont font;

	public TitleScreen(Game game) {
		super(game);
	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		font.draw(batch, "PONG \nthe RETURN", Gdx.graphics.getWidth()/2,  Gdx.graphics.getHeight()/2);	
		batch.end();

		if (Gdx.input.isKeyPressed(Keys.ANY_KEY)) {
			game.setScreen(new GameScreen(game));
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

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
		batch.dispose();
		font.dispose();
	}

}
