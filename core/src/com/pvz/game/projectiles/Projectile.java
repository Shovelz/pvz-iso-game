package com.pvz.game.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Projectile {

	protected float arc;
	protected float speed;
	protected Texture sprite;
	protected Vector2 target;
	protected Vector2 start;
	protected Vector2 location;
	protected int damage;

	public Projectile(float arc, float speed, Texture sprite, Vector2 start, int damage, Vector2 target) {
		this.arc = arc;
		this.speed = speed;
		this.sprite = sprite;
		this.target = target;
		this.start = start;
		this.damage = damage;
		this.location = start;
	}

	public Projectile(float arc, float speed, Texture sprite, Vector2 start, int damage) {
		this(arc, speed, sprite, start, damage, null);

	}

	public abstract void render(SpriteBatch batch, float delta);
	public abstract void update(float delta);
	public abstract void move(float delta);
	
	/**
	 * @return the arc
	 */
	public float getArc() {
		return arc;
	}

	/**
	 * @param arc the arc to set
	 */
	public void setArc(float arc) {
		this.arc = arc;
	}

	/**
	 * @return the speed
	 */
	public float getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * @return the sprite
	 */
	public Texture getSprite() {
		return sprite;
	}

	/**
	 * @param sprite the sprite to set
	 */
	public void setSprite(Texture sprite) {
		this.sprite = sprite;
	}
	
	
}
