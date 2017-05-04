package entity;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {

	private float height, width;
	private Vector2 position;
	public Vector2 velocity;
	private Vector2 accel;
	private Rectangle rect;

	private boolean moving = false;

	public Entity(Rectangle rect) {
		this.height = rect.getHeight();
		this.width = rect.getWidth();
		this.setRect(rect);
		position = new Vector2(rect.x, rect.y);
		velocity = new Vector2(MathUtils.randomSign() * MathUtils.random(300),
				MathUtils.randomSign() * MathUtils.random(300));
		
	}

	public void update(float delta) {
		if (moving) {
			position.x += velocity.x * delta;
			position.y += velocity.y * delta;
			setRect(new Rectangle(position.x, position.y, width, height));
		}
	}

	public void draw(ShapeRenderer renderer) {
		renderer.rect(getRect().x, getRect().y, getRect().width, getRect().height);
	}

	public Vector2 getPosition() {
		return rect.getPosition(position);
	}

	public void setPosition(Vector2 position) {
		rect.setPosition(position);
	}

	public Vector2 getVelocity() {
		return velocity;
	}

	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}

	public Vector2 getAccel() {
		return accel;
	}

	public void setAccel(Vector2 accel) {
		this.accel = accel;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public Rectangle getRect() {
		return rect;
	}

	public void setRect(Rectangle rect) {
		this.rect = rect;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

}
