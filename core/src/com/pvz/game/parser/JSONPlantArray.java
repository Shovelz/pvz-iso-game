package com.pvz.game.parser;

import java.util.ArrayList;
import java.util.List;

public class JSONPlantArray {

	List<JSONPlant> plants = new ArrayList<JSONPlant>();
}

class JSONPlant{
	String name;
	int cost;
	float recharge;
	int health;
	int damage;
	float interval;
	String range;

	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public float getRecharge() {
		return recharge;
	}
	public void setRecharge(float recharge) {
		this.recharge = recharge;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public float getInterval() {
		return interval;
	}
	public void setInterval(float interval) {
		this.interval = interval;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}

	
}