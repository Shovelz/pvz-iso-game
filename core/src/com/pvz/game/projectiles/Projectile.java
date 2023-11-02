package com.pvz.game.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Projectile {

	private float arc;
	private int speed;
	private Texture sprite;
	private Vector2 target;
	private Vector2 start;
	private int damage;

	public Projectile(float arc, int speed, Texture sprite, Vector2 start, int damage, Vector2 target) {
		this.arc = arc;
		this.speed = speed;
		this.sprite = sprite;
		this.target = target;
		this.start = start;
		this.damage = damage;
	}

	public Projectile(float arc, int speed, Texture sprite, Vector2 start, int damage) {
		this(arc, speed, sprite, start, damage, null);

	}
	public abstract void render(SpriteBatch batch, float delta);
	public abstract void update(float delta);
	
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
	public int getSpeed() {
		return speed;
	}

	/**
	 * @param speed the speed to set
	 */
	public void setSpeed(int speed) {
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
