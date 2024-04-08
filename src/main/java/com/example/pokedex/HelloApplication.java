package com.example.pokedex;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 800);
        scene.getStylesheets().add(getClass().getResource("Style.css").toExternalForm());
        Image image = new Image("C:\\Users\\User\\IdeaProjects\\PokemonStats2\\src\\main\\java\\com\\example\\pokemonstats2\\Pokeball.png");

        stage.getIcons().add(image);

        stage.setTitle("Pokedex");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}