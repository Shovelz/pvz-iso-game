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

	public float getArc() {
		return arc;
	}

	public void setArc(float arc) {
		this.arc = arc;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public Texture getSprite() {
		return sprite;
	}

	public void setSprite(Texture sprite) {
		this.sprite = sprite;
	}

	public Vector2 getTarget() {
		return target;
	}

	public void setTarget(Vector2 target) {
		this.target = target;
	}

	public Vector2 getStart() {
		return start;
	}

	public void setStart(Vector2 start) {
		this.start = start;
	}

	public Vector2 getLocation() {
		return location;
	}

	public void setLocation(Vector2 location) {
		this.location = location;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	
	
	
}
