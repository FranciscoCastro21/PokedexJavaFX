module com.example.pokedex {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;


    opens com.example.pokedex to javafx.fxml;
    exports com.example.pokedex;
}