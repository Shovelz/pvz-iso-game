package com.pvz.game;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

public class Tilemap {

	public List<Tile> base = new LinkedList<Tile>();
	public List<Tile> objects = new LinkedList<Tile>();
	public List<Tile> background = new LinkedList<Tile>();
	private Texture hill;
	private Texture water;
	private Texture grass;
	private Texture grassHigh;
	private Texture box;
	private Texture peashooter;
	private Texture houseBG;

	private String[][] map = {
			{"0", "0", "0", "0", "0", "0", "0", "0", "0"},
			{"0", "0", "0", "0", "0", "0", "0", "0", "0"},
			{"0", "0", "0", "0", "0", "0", "0", "0", "0"},
			{"0", "0", "0", "0", "0", "0", "0", "0", "0"},
			{"0", "0", "0", "0", "0", "0", "0", "0", "0"},
			{"0", "0", "0", "0", "0", "0", "0", "0", "0"},
	}; 

	public static final float TILE_WIDTH = 48;
	public static final float TILE_HEIGHT = 48;


	public Tilemap() {
		grass = new Texture("grass3.png");
		grass.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		grassHigh = new Texture("grass3H.png");
		water = new Texture("water.png");
		hill = new Texture("hill.png");
		box = new Texture("box.png");
		peashooter = new Texture("peashooter.png");
		houseBG = new Texture("background6x9.png");

		fillMap();
	}

	public void render(SpriteBatch batch) {

//		for(Tile t : base) {
//			t.render(batch);
//		}

//		for(Tile t : background) {
//			t.render(batch);
//		}
		for(Tile t : objects) {
			t.render(batch);
		}

	}

	public void fillMap() {

		Random rand = new Random();
		for(int row = map.length - 1; row >= 0; row--) {
			for(int col = map[row].length - 1; col >= 0; col--) {
				float x = (row - col) * TILE_WIDTH/ 2f;
				float y = (col + row) * TILE_HEIGHT/ 2f;
				//For objects ( cause why add more empty space in the images)

				Tile t = null;
//				if(map[row][col].equals("0")) {
//					t = new Tile(grass, new Vector2(row, col), new Vector2(x, y));
//					base.add(t);
//				} else if(map[row][col].equals("1")){
//					base.add(new Tile(water, new Vector2(row, col), new Vector2(x, y)));
//				}
				int r = rand.nextInt(10);
				if(r == 5) {
					objects.add(new Tile(peashooter, new Vector2(row, col), new Vector2(x, y)));
					//										this.set(t);
				}

			}
		}	
		background.add(new Tile(houseBG, new Vector2(0, 0), new Vector2(-272, -35)));

	}

	public Tile get(float worldX, float worldY) {
		float halfWidth = TILE_WIDTH / 2f;
		float halfHeight = TILE_HEIGHT / 2f;

		int col = (int) Math.floor((worldY / halfHeight - (worldX / halfWidth - 1f)) / 2 );
		int row = (int) Math.floor(((worldX / halfWidth + worldY / halfHeight) - 1f) / 2);

		if (row < 0 || row >= map.length || col < 0 || col >= map[0].length) {
			// Handle the case where the coordinates are outside the map bounds.
			return null;
		}

		Vector2 tilemapPos = new Vector2(row, col);
		float x = (row - col) * halfWidth;
		float y = (col + row) * halfHeight;

		if (map[row][col].equals("0")) {
			return new Tile(grass, tilemapPos, new Vector2(x, y));
		} else if (map[row][col].equals("1")) {
			return new Tile(water, tilemapPos, new Vector2(x, y));
		} else {
			// Handle other cases if needed.
			return null;
		}
	}




	public void set(Tile t) {

		base.stream()
		.filter(tile -> tile.tilemapPos.equals(t.tilemapPos))
		.findFirst()
		.ifPresent(tile -> {
			tile.setTexture(grassHigh);
			tile.worldPos.y -= 5;
		}); 
	}

	public void resetTileTexture(Tile t) {
		base.stream()
		.filter(tile -> tile.tilemapPos.equals(t.tilemapPos))
		.findFirst()
		.ifPresent(tile -> {
			tile.setTexture(grass);
			tile.worldPos.y += 5; 
		});
	}	

}