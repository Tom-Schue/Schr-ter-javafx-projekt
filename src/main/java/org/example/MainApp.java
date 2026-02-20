package org.example;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        // ---------------- TOPBAR ----------------
        Label title = new Label("IMPROVE");
        Label streak = new Label("Streak: 5 🔥");

        HBox topBar = new HBox(title, new Region(), streak);
        HBox.setHgrow(topBar.getChildren().get(1), Priority.ALWAYS);
        topBar.setPadding(new Insets(20));
        topBar.setAlignment(Pos.CENTER_LEFT);


        // ---------------- SIDEBAR ----------------
        VBox sidebar = new VBox(30);
        sidebar.setPadding(new Insets(30));
        sidebar.setStyle("-fx-background-color: #CFC6BA ;");

        Button homeBtn = createImageButton("icon.png");
        Button tdlBtn = createImageButton("icon.png");
        Button sleepBtn = createImageButton("icon.png");
        Button statsBtn = createImageButton("icon.png");

        sidebar.getChildren().addAll(homeBtn, tdlBtn, sleepBtn, statsBtn);
        sidebar.setAlignment(Pos.TOP_CENTER);


        // ---------------- CONTENT AREA ----------------
        Label quote = new Label("\"Discipline beats motivation.\"");
        quote.setStyle("-fx-font-style: italic;");

        StackPane contentArea = new StackPane(quote);
        contentArea.setAlignment(Pos.CENTER);


        // ---------------- ROOT LAYOUT ----------------
        BorderPane root = new BorderPane();
        root.setTop(topBar);
        root.setLeft(sidebar);
        root.setCenter(contentArea);

        // ---------------- BACKGROUND GRADIENT ----------------
        root.setBackground(
            new Background(
                new BackgroundFill(
                    new LinearGradient(
                        0, 0, 0, 1, true,
                        CycleMethod.NO_CYCLE,
                        new Stop(0, Color.web("#FFF5EB")),
                        new Stop(1, Color.web("#FFEAD5"))
                    ),
                    CornerRadii.EMPTY,
                    Insets.EMPTY
                )
            )
        );

        Scene scene = new Scene(root, 1600, 900);


        // ---------------- RESPONSIVE SCALING ----------------

        // Titel sehr groß
        title.styleProperty().bind(
                Bindings.concat("-fx-font-size: ",
                        scene.widthProperty().divide(18).asString(),
                        "px; -fx-font-weight: bold;")
        );

        streak.styleProperty().bind(
                Bindings.concat("-fx-font-size: ",
                        scene.widthProperty().divide(45).asString(), "px;")
        );

        quote.styleProperty().bind(
                Bindings.concat("-fx-font-size: ",
                        scene.widthProperty().divide(28).asString(),
                        "px; -fx-font-style: italic;")
        );

        // Sidebar‑Breite dynamisch
        sidebar.prefWidthProperty().bind(scene.widthProperty().divide(10));

        // Icon‑Größe dynamisch
        sidebar.getChildren().forEach(node -> {
            if (node instanceof Button btn && btn.getGraphic() instanceof ImageView img) {
                img.fitWidthProperty().bind(scene.widthProperty().divide(20));
            }
        });


        stage.setTitle("Improve Dashboard");
        stage.setScene(scene);
        stage.show();
    }


    // ---------------- HILFSMETHODE: Bild-Button ----------------
    private Button createImageButton(String imagePath) {
        ImageView img = new ImageView(new Image(imagePath));
        img.setPreserveRatio(true);

        Button btn = new Button();
        btn.setGraphic(img);
        btn.setStyle("-fx-background-color: transparent;");
        btn.setMaxWidth(Double.MAX_VALUE);

        return btn;
    }


    public static void main(String[] args) {
        launch();
    }
}
