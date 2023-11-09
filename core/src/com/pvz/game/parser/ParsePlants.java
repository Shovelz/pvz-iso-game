package com.pvz.game.parser;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pvz.game.plants.Plant;

public class ParsePlants {

    private static URL getUrlFromString(String path) {
        return JSONPlantArray.class.getClassLoader().getResource(path);
    }
    
    public static Path getPathFromString(String pathFromSource) throws IOException, URISyntaxException {
        URL url = getUrlFromString(pathFromSource);
        if (url == null) {
            throw new IOException();
        }
        URI uri = url.toURI();
        return Paths.get(uri.toString());

    }

	public void load() {
		
        try {
            String pathFromSource = "plants/plants.json";

            String json = new String(Files.readAllBytes(getPathFromString(pathFromSource)));
            
            ObjectMapper objectMapper = new ObjectMapper();
            List<Plant> plantList = objectMapper.readValue(json, new TypeReference<List<Plant>>() {});

            // Convert Plant objects to AbstractPlant and add to the queue
            for (Plant plant : plantList) {
            	Plant abstractPlant = new Plant(plant.getDamage(), plant.getTime());
                actionQueue.offer(abstractPlant);
            }
//	public Plant(Texture texture, Texture spriteSheet, int damage, int health, float reload, int cost, int range, float recharge, Projectile projectile) {

        } catch (IOException e) {
            e.printStackTrace();
//            throw new ActionNotFoundException();
        } catch (Exception e) {
            e.printStackTrace();
//            throw new ActionParserException();
        }


	}
}

