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
import com.pvz.game.tiles.BackgroundTile;
import com.pvz.game.tiles.PlantTile;
import com.pvz.game.tiles.Tile;

public class Tilemap {

	public List<PlantTile> plants= new LinkedList<PlantTile>();
	public List<Tile> zombies= new LinkedList<Tile>();
	public List<PlantTile> base = new LinkedList<PlantTile>();
	private Texture grass;
	private Texture peashooter;
	private Texture houseBG;
	private TiledMap isoMap;
	private Vector2 baseCorner = new Vector2();
	private BackgroundTile background;
	//This is really horrible, but since im using an overlayed image these offsets have to exist
	private Vector2 horribleBackgroundOffset = new Vector2(-(6*TILE_WIDTH) + (TILE_WIDTH/4) + 1, -TILE_HEIGHT/2 +2);
	

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
		grass = new Texture("grass.png");
		grass.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		peashooter = new Texture("peashooter.png");
		houseBG = new Texture("background6x9.png");

		baseCorner.x = isoMap.getLayers().get(0).getOffsetX(); 
		baseCorner.y = isoMap.getLayers().get(0).getOffsetY();
	}

	public void render(SpriteBatch batch) {
		for(PlantTile t : base) {
//			t.render(batch);
		}

		background.render(batch);

		for(PlantTile t : plants) {
			t.render(batch);
		}


	}

	public void fillMap() {

		Vector2 corner = baseCorner.add(new Vector2((map.length) *(TILE_WIDTH/2) + TILE_WIDTH /2, -(map[map.length-1].length) *(TILE_HEIGHT/4) ));
		background = new BackgroundTile(houseBG, new Vector2(0, 0), new Vector2(baseCorner.x + horribleBackgroundOffset.x , baseCorner.y + horribleBackgroundOffset.y ));
		for(int row = 1;row < map.length+1; row++) {
			for(int col = 0; col < map[row-1].length;col++) {
				float x = corner.x + ((row - col) * TILE_WIDTH/2);
				float y = corner.y + ((col + row) * TILE_HEIGHT/4);
				base.add(new PlantTile(peashooter, new Vector2(row, col), new Vector2(x,y)));
				plants.add(new PlantTile(peashooter, new Vector2(row, col), new Vector2(x,y)));
			}
		}


	}




	public PlantTile get(float mouseX, float mouseY) {
	    for (PlantTile tile : base) {
	        Vector2 tilePos = tile.getWorldPos();
	        float tileX = tilePos.x - baseCorner.x;
	        float tileY = tilePos.y - baseCorner.y;

	        float isoX = (mouseX - baseCorner.x - TILE_WIDTH / 2) / (TILE_WIDTH / 2);
	        float isoY = (mouseY - baseCorner.y - TILE_HEIGHT / 4) / (TILE_HEIGHT / 4);

	        float tileMouseX = (isoY + isoX) / 2;
	        float tileMouseY = (isoY - isoX) / 2;

	        if (Math.round(tileMouseX) == tile.getTilemapPos().x && Math.round(tileMouseY) == tile.getTilemapPos().y) {
	            return tile;
	        }
	    }
	    return null;
	}

	


	public void set(Tile t) {

		base.stream()
		.filter(tile -> tile.getTilemapPos().equals(t.getTilemapPos()))
		.findFirst()
		.ifPresent(tile -> {
//			tile.setTexture(grassHigh);
			tile.getWorldPos().y -= 5;
		}); 
	}

	public void resetTileTexture(Tile t) {
		base.stream()
		.filter(tile -> tile.getTilemapPos().equals(t.getTilemapPos()))
		.findFirst()
		.ifPresent(tile -> {
//			tile.setTexture(grass);
			tile.getWorldPos().y += 5; 
		});
	}	

}