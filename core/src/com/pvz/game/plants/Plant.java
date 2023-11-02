package com.pvz.game.plants;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.pvz.game.projectiles.Projectile;

public class Plant{

	private Texture texture;
	private Texture spriteSheet;
	private int damage;
	private int health;
	//	private List<Effect> effects = new List<Effect>(); 
	private float reload; // reload speed
	private int cost; //sun cost

	private int range; //range
	private float recharge; //seed packet time
	private Projectile projectile;

	private TextureRegion[] animationFrames;
	private Animation<TextureRegion> animation;

	public Plant(Texture texture, Texture spriteSheet, int damage, int health, float reload, int cost, int range, float recharge, Projectile projectile) {
		this.texture = texture;
		this.spriteSheet = spriteSheet;
		this.damage = damage;
		this.health = health;
		this.reload = reload;
		this.cost = cost;
		this.range = range;
		this.recharge = recharge;
		this.projectile = projectile;

		loadAnimations();
	}

	//Where loading animations in the Plant and not the Tiles so we only have to load it once 
	private void loadAnimations() {

		TextureRegion[][] tmpFrames = TextureRegion.split(spriteSheet, 48, 48);
//		for(int row = 0; row < tmpFrames.length;row++) {
//			for(int col = 0; col < tmpFrames[tmpFrames.length-1].length; col++) {
//				System.out.print("Row: " + row + ", Col: " + col + ", Texture: ");
//				System.out.println(tmpFrames[row][col]);
//			}
//		}

		animationFrames = new TextureRegion[tmpFrames.length * tmpFrames[0].length];
		int index = 0;
		for(int row = 0; row < tmpFrames.length;row++) {
			for(int col = 0; col < tmpFrames[tmpFrames.length-1].length; col++) {
				animationFrames[index++] = tmpFrames[row][col];
			}

		}
		animation = new Animation<TextureRegion>(1f/4f, animationFrames);


	}

	public Texture getTexture() {
		return texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Texture getSpriteSheet() {
		return spriteSheet;
	}

	public void setSpriteSheet(Texture spriteSheet) {
		this.spriteSheet = spriteSheet;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getReload() {
		return reload;
	}

	public void setReload(float reload) {
		this.reload = reload;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public float getRecharge() {
		return recharge;
	}

	public void setRecharge(float recharge) {
		this.recharge = recharge;
	}

	public Projectile getProjectile() {
		return projectile;
	}

	public void setProjectile(Projectile projectile) {
		this.projectile = projectile;
	}

	public TextureRegion[] getAnimationFrames() {
		return animationFrames;
	}

	public void setAnimationFrames(TextureRegion[] animationFrames) {
		this.animationFrames = animationFrames;
	}

	public Animation<TextureRegion> getAnimation() {
		return animation;
	}

	public void setAnimation(Animation<TextureRegion> animation) {
		this.animation = animation;
	}


}
