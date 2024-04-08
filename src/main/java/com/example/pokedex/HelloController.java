package com.example.pokedex;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;

public class HelloController {

    @FXML
    private ListView<String> pokemonListView;

    @FXML
    private TextArea pokemonStatsArea;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    private Pokedex pokedex;

    public void initialize() {
        pokedex = new Pokedex();
        try {
            updatePokemonList();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        pokemonListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                updatePokemonStats(newValue);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        //usng the serach pokemon while clicking

        searchButton.setOnAction(event -> {
            String searchTerm = searchField.getText().trim();
            if (!searchTerm.isEmpty()) {
                try {
                    searchPokemon(searchTerm);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    //
    private void updatePokemonList() throws IOException, InterruptedException {
        pokemonListView.getItems().addAll(pokedex.getAllPokemonNames());
    }

    private void updatePokemonStats(String pokemonName) throws IOException, InterruptedException {
        String stats = pokedex.getPokemonStats(pokemonName);
        pokemonStatsArea.setText(stats);
    }

    private void searchPokemon(String searchTerm) throws IOException, InterruptedException {
        pokemonListView.getItems().clear();
        pokemonListView.getItems().addAll(pokedex.searchPokemon(searchTerm));
    }
}