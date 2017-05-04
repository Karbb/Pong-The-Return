package com.karbb.pong;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MyInputProcessor implements InputProcessor {

	World world;

	Mouse mouse;

	public MyInputProcessor(World world) {
		this.world = world;
		this.mouse = world.mouse;
	}

	public boolean keyDown(int keycode) {
		switch (keycode) {

		case Keys.LEFT:

			break;

		case Keys.RIGHT:

			break;

		case Keys.A:

			break;

		case Keys.D:

			break;

		case Keys.SPACE:

			break;

		case Keys.ENTER:
			world.setSpawn(true);
			break;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.ENTER:
			world.setSpawn(false);
			break;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mouse.setMousePosition(screenX, World.WORLD_HEIGHT - screenY);
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// ignore if its not left mouse button or first touch pointer
		if (button != Input.Buttons.LEFT || pointer > 0)
			return false;
		mouse.setMousePosition(screenX, World.WORLD_HEIGHT - screenY);
		mouse.setDragging(true);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mouse.setMousePosition(screenX, World.WORLD_HEIGHT - screenY);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button != Input.Buttons.LEFT || pointer > 0)
			return false;
		mouse.setMousePosition(screenX, World.WORLD_HEIGHT - screenY);
		mouse.setDragging(false);
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
