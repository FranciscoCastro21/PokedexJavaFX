package com.example.pokedex;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Pokedex {
    //Access to first API
    private final String API_URL = "https://pokeapi.co/api/v2/pokemon?limit=721";

    //Get the pokemons from the API given by a HTTP request
    public List<String> getAllPokemonNames() throws IOException, InterruptedException {
        List<String> pokemonNames = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();
        //response from the request as a string
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonObject = new Gson().fromJson(response.body(), JsonObject.class);
        // turning into gson from the key result
        JsonArray results = jsonObject.getAsJsonArray("results");
        for (JsonElement result : results) {
            JsonObject pokemon = result.getAsJsonObject();
            pokemonNames.add(pokemon.get("name").getAsString());
        }

        return pokemonNames;
    }
        //entering the new API )my APi is splitted in two to get the Stats
    public String getPokemonStats(String pokemonName) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + pokemonName))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonObject = new Gson().fromJson(response.body(), JsonObject.class);

        StringBuilder stats = new StringBuilder();
        stats.append(pokemonName).append(" Stats").append(":\n");
    // Retriving the data from the second api to show the stats of each pokemon
        JsonArray statsArray = jsonObject.getAsJsonArray("stats");
        for (JsonElement stat : statsArray) {
            JsonObject statObject = stat.getAsJsonObject();
            String statName = statObject.getAsJsonObject("stat").get("name").getAsString();
            int statValue = statObject.get("base_stat").getAsInt();
            stats.append(statName).append(": ").append(statValue).append("\n");
        }

        return stats.toString();
    }
        //create an array list to store what we puut in the search bar and compare it to all the pokemon names
        //  using our method from b4, and return if there is any matching that contains the letetrs written
    public List<String> searchPokemon(String searchTerm) throws IOException, InterruptedException {
        List<String> matchingPokemon = new ArrayList<>();
        List<String> allPokemon = getAllPokemonNames();
        for (String pokemonName : allPokemon) {
            if (pokemonName.toLowerCase().contains(searchTerm.toLowerCase())) {
                matchingPokemon.add(pokemonName);
            }
        }
        return matchingPokemon;
    }
}
