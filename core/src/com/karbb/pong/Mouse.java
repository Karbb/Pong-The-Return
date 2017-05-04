package com.karbb.pong;

import com.badlogic.gdx.math.Vector2;

public class Mouse {

	private Vector2 mousePosition;
	private boolean dragging;

	public Mouse(Vector2 mousePosition, boolean dragging) {
		this.mousePosition = mousePosition;
		this.dragging = dragging;
	}

	public Vector2 getMousePosition() {
		return mousePosition;
	}

	public void setMousePosition(Vector2 mousePosition) {
		this.mousePosition = mousePosition;
	}

	public void setMousePosition(float x, float y) {
		Vector2 vec = new Vector2(x, y);
		this.mousePosition = vec;
	}

	public boolean isDragging() {
		return dragging;
	}

	public void setDragging(boolean dragging) {
		this.dragging = dragging;
	}

}
