package com.karbb.pong;

import java.util.Iterator;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import entity.Entity;

public class World {

	public static final float WORLD_WIDTH = 800;
	public static final float WORLD_HEIGHT = 600;
	public static final long SPAWN_RATE = 1 * 500;

	public long nextSpawn = 0;
	public long rectSpawned = 0;

	Mouse mouse;
	boolean debug, spawn = false;

	public boolean isSpawn() {
		return spawn;
	}

	public void setSpawn(boolean spawn) {
		this.spawn = spawn;
	}

	private BitmapFont debugFont;
	private BitmapFont font;

	LinkedList<Entity> entityList;

	public World() {
		debugFont = new BitmapFont();
		debugFont.setColor(Color.WHITE);

		font = new BitmapFont();
		font.setColor(Color.RED);
		entityList = new LinkedList<Entity>();

		mouse = new Mouse(new Vector2(), false);
		Gdx.input.setInputProcessor(new MyInputProcessor(this));
	}

	public void draw(SpriteBatch batch, ShapeRenderer renderer) {
		renderer.begin(ShapeType.Filled);
		if (entityList.size() > 10) {
			entityList.removeFirst();
		}
		entityList.forEach((entity) -> {
			entity.draw(renderer);
		});

		renderer.end();

		batch.begin();

		if (debug) {

			int index = 0;
			GlyphLayout fontLayout = new GlyphLayout(font, "1");
			for (Entity entity : entityList) {
				font.draw(batch, "" + index, entity.getPosition().x, entity.getPosition().y + fontLayout.height + 1);
				index++;
			}

			String fps = "" + Gdx.graphics.getFramesPerSecond();
			GlyphLayout fpsLayout = new GlyphLayout(debugFont, "Dragging: true");
			fpsLayout.width += 10;
			fpsLayout.height += 5;
			debugFont.draw(batch, "FPS: " + fps, Gdx.graphics.getWidth() - fpsLayout.width, fpsLayout.height);
			debugFont.draw(batch, "" + spawn, Gdx.graphics.getWidth() - fpsLayout.width, 2 * fpsLayout.height);
			debugFont.draw(batch, rectSpawned + "", Gdx.graphics.getWidth() - fpsLayout.width, 3 * fpsLayout.height);
			debugFont.draw(batch, spawnDelay + "", Gdx.graphics.getWidth() - fpsLayout.width, 4 * fpsLayout.height);
			debugFont.draw(batch, "[" + mouse.getMousePosition().x + ", " + mouse.getMousePosition().y + "]",
					Gdx.graphics.getWidth() - fpsLayout.width, 5 * fpsLayout.height);
			debugFont.draw(batch, "Dragging: " + mouse.isDragging(), Gdx.graphics.getWidth() - fpsLayout.width,
					6 * fpsLayout.height);

		}
		batch.end();
	}

	private float spawnDelay;

	public void update(float deltaTime) {
		checkDebug();
		spawnEntity();
		checkMouse();
		checkCollisions(deltaTime);

		entityList.forEach((entity) -> {
			entity.update(deltaTime);
		});
	}

	private void checkCollisions(float deltatime) {
		entityList.forEach((entity) -> {
			if ((entity.getRect().x < 0 && entity.getVelocity().x < 0)
					|| (entity.getRect().x + entity.getWidth() > WORLD_WIDTH && entity.getVelocity().x > 0)) {
				entity.velocity.x = -entity.velocity.x;
			} else if ((entity.getRect().y < 0 && entity.getVelocity().y < 0)
					|| (entity.getRect().y + +entity.getHeight() > WORLD_HEIGHT && entity.getVelocity().y > 0)) {
				entity.velocity.y = -entity.velocity.y;
			}
		});

	}

	private void checkMouse() {
		for (Entity entity : entityList) {
			Rectangle rect = entity.getRect();
			if (rect.contains(mouse.getMousePosition()) && mouse.isDragging()) {
				entity.setMoving(true);
				// System.out.println(entity.isMoving());
			}
		}

	}

	private void spawnEntity() {
		if (System.currentTimeMillis() >= nextSpawn && spawn) {

			Rectangle futureRect = null;

			boolean rectOverlapped = false;
			do {
				rectOverlapped = false;
				float side = MathUtils.random(10, 50);
				futureRect = new Rectangle(MathUtils.random(0, WORLD_WIDTH - side),
						MathUtils.random(0, WORLD_HEIGHT - side), side, side);

				for (Iterator<Entity> iterator = entityList.iterator(); iterator.hasNext();) {
					Entity entity = (Entity) iterator.next();

					if (entity.getRect().overlaps(futureRect)) {
						rectOverlapped = true;
					}
				}
			} while (rectOverlapped);

			entityList.add(new Entity(futureRect));
			rectSpawned++;
			nextSpawn = System.currentTimeMillis() + SPAWN_RATE;
		}
	}

	private void checkDebug() {
		if (Gdx.input.isKeyJustPressed(Keys.O)) {
			debug = !debug;
		}
	}
}
