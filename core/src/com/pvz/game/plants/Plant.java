package com.pvz.game.plants;

import com.badlogic.gdx.graphics.Texture;
import com.pvz.game.projectiles.Projectile;

public class Plant{

	private Texture texture;
	private int damage;
	private int health;
	//	private List<Effect> effects = new List<Effect>(); 
	private float reload; // reload speed
	private int cost; //sun cost

	private int range; //range
	private float recharge; //seed packet time
	private Projectile projectile;

	public Plant(Texture texture, int damage, int health, float reload, int cost, int range, float recharge, Projectile projectile) {
		this.texture = texture;
		this.damage = damage;
		this.health = health;
		this.reload = reload;
		this.cost = cost;
		this.range = range;
		this.recharge = recharge;
		this.projectile = projectile;
	}



}
