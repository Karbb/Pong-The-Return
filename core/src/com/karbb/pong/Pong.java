package com.karbb.pong;

import com.badlogic.gdx.Game;
import com.karbb.pong.screen.TitleScreen;

public class Pong extends Game {

	@Override
	public void create() {
		setScreen(new TitleScreen(this));
	}
	
	
	
}
