package com.pvz.game;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pvz.game.tiles.PlantTile;
import com.pvz.game.tiles.Tile;

public class Tilemap {

	public List<Tile> base = new LinkedList<Tile>();
	public List<PlantTile> objects = new LinkedList<PlantTile>();
	public List<Tile> background = new LinkedList<Tile>();
	private Texture grass;
	private Texture grassHigh;
	private Texture peashooter;
	private Texture houseBG;
	private TiledMap isoMap;

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


	public Tilemap(TiledMap iso) {
		isoMap = iso;
		grass = new Texture("grass3.png");
		grass.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		grassHigh = new Texture("grass3H.png");
		peashooter = new Texture("peashooter.png");
		houseBG = new Texture("background6x9.png");

		fillMap();
	}

	public void render(SpriteBatch batch) {

		for(PlantTile t : objects) {
			t.render(batch);
		}

	}

	public void fillMap() {

		
		float groundX = isoMap.getLayers().get(0).getOffsetX() + (map.length) *(TILE_WIDTH/2) + TILE_WIDTH /2;
		float groundY = isoMap.getLayers().get(0).getOffsetY() - (map[map.length-1].length) *(TILE_HEIGHT/4) ;

		for(int row = 1;row < map.length+1; row++) {
			for(int col = 0; col < map[row-1].length;col++) {
				float x = groundX + ((row - col) * TILE_WIDTH/2);
				float y = groundY + ((col + row) * TILE_HEIGHT/4);
				objects.add(new PlantTile(peashooter, new Vector2(row, col), new Vector2(x,y)));
			}
		}


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
			return new PlantTile(grass, tilemapPos, new Vector2(x, y));
		} else if (map[row][col].equals("1")) {
//			return new PlantTile(water, tilemapPos, new Vector2(x, y));
		} else {
			// Handle other cases if needed.
			return null;
		}
	}




	public void set(Tile t) {

		base.stream()
		.filter(tile -> tile.getTilemapPos().equals(t.getTilemapPos()))
		.findFirst()
		.ifPresent(tile -> {
			tile.setTexture(grassHigh);
			tile.getWorldPos().y -= 5;
		}); 
	}

	public void resetTileTexture(Tile t) {
		base.stream()
		.filter(tile -> tile.getTilemapPos().equals(t.getTilemapPos()))
		.findFirst()
		.ifPresent(tile -> {
			tile.setTexture(grass);
			tile.getWorldPos().y += 5; 
		});
	}	

}