package org.example;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToDoView {

    public static Scene createScene(Stage stage) {
        To_Do_List liste = new To_Do_List();

        ListView<String> listView = new ListView<>();
        Button btnAdd = new Button("Hinzufügen");
        Button btnBack = new Button("Zurück");

        btnAdd.setOnAction(e -> {
            liste.hinzufuegen("Neue Aufgabe");
            // Refresh der ListView - wird später durch bessere Logik ersetzt
        });

        btnBack.setOnAction(e -> new MainApp().start(stage));

        VBox layout = new VBox(10, listView, btnAdd, btnBack);
        return new Scene(layout, 300, 300);
    }
}

