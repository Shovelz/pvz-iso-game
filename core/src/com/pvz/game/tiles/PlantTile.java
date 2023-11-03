package com.pvz.game.tiles;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.pvz.game.plants.Plant;
import com.pvz.game.projectiles.Projectile;

public class PlantTile extends AbstractTile{


	private TextureRegion[] animationFrames;
	private Animation<TextureRegion> animation;
	private Plant plant;
	private float time = 0;
	public final static int animSpeed = 2;
	private float fireTime = 0;

	private List<Projectile> projectiles = new ArrayList<Projectile>();

	public PlantTile(Texture t, Vector2 tileMapPos, Vector2 worldPos) {
		super(t, tileMapPos, worldPos);


	}
	@Override
	public void render(SpriteBatch batch) {}

	public void render(SpriteBatch batch, float delta) {
		renderProjectiles(batch, delta);
		if(texture != null) {
			fireTime += delta;
			if(fireTime >= plant.getReload()) {
				projectiles.add(plant.fire());
				fireTime -= plant.getReload();
			}
			time += animSpeed * delta;
			batch.draw(animation.getKeyFrame(time, true), worldPos.x, worldPos.y);
		}
	}
	public void renderProjectiles(SpriteBatch batch, float delta) {

		for(Projectile pro : projectiles) {
			moveProjectile(pro, delta);
			pro.render(batch, delta);
		}
	}

	public void moveProjectile(Projectile pro, float delta) {
		pro.move(delta);
	}

	@Override
	public void setTexture(Texture tex) {
		this.texture = tex;
	}

	public void setAnimation(Animation<TextureRegion> anim, TextureRegion[] frames) {
		this.animationFrames = frames;
		this.animation = anim;
	}

	public Plant getPlant() {
		return plant;
	}

	public void setPlant(Plant plant) {
		this.plant = plant;
	}


}