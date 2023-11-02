package com.pvz.game.tiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.pvz.game.plants.Plant;

public class PlantTile extends AbstractTile{
	
	
	private TextureRegion[] animationFrames;
	private Animation<TextureRegion> animation;
	private Plant plant;

	public PlantTile(Texture t, Vector2 tileMapPos, Vector2 worldPos) {
		super(t, tileMapPos, worldPos);
		
		
	}
	@Override
	public void render(SpriteBatch batch) {}
	
	public void render(SpriteBatch batch, float time) {
		if(texture != null) {
			batch.draw(animation.getKeyFrame(time, true), worldPos.x, worldPos.y);
		}
	}
	
	@Override
	public void setTexture(Texture tex) {
		this.texture = tex;
	}
	
	public void setAnimation(Animation<TextureRegion> anim, TextureRegion[] frames) {
		this.animationFrames = frames;
		this.animation = anim;
	}
	
	
}