package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        // Zentrum: großer, fetter Schriftzug "Improve"
        Label title = new Label("Improve");
        title.setFont(Font.font("System", FontWeight.BOLD, 90));
        StackPane center = new StackPane(title);
        center.setPadding(new Insets(20));
        VBox.setVgrow(center, Priority.ALWAYS);

        // Erinnerungen Bereich mit Label und Buttons
        Label reminderField = new Label("Reminder");
        reminderField.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));

        Button TDL_button = new Button("To-Do-List");
        TDL_button.setStyle("-fx-padding: 10px 20px; -fx-font-size: 14;");
        TDL_button.setOnAction(e -> {
            Scene toDoScene = ToDoView.createScene(stage);
            stage.setScene(toDoScene);
        });

        Button SleepTracker_button = new Button("Sleep Tracker");
        SleepTracker_button.setStyle("-fx-padding: 10px 20px; -fx-font-size: 14;");

        HBox buttonBox = new HBox(10, TDL_button, SleepTracker_button);
        buttonBox.setAlignment(Pos.CENTER);

        HBox reminderBox = new HBox(20, reminderField, buttonBox);
        reminderBox.setAlignment(Pos.CENTER);
        reminderBox.setPadding(new Insets(20));

        // Streak Label oben rechts
        Label Streak_Label = new Label("Streak");
        Streak_Label.setFont(Font.font("System", FontWeight.BOLD, 20));
        Streak_Label.setStyle("-fx-text-fill: #FC876A; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-padding: 5px;");

        HBox topBox = new HBox(Streak_Label);
        topBox.setPadding(new Insets(10));
        topBox.setAlignment(Pos.TOP_RIGHT);

        // Icon links
        URL iconResource = getClass().getResource("/icon.png");
        String iconPath = (iconResource != null) ? iconResource.toExternalForm() : null;
        ImageView imageView = null;

        if (iconPath != null) {
            Image img = new Image(iconPath);
            imageView = new ImageView(img);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
        }

        VBox leftBox = new VBox(imageView);
        leftBox.setPadding(new Insets(10));
        leftBox.setAlignment(Pos.TOP_LEFT);

        // Root Layout
        BorderPane root = new BorderPane();
        root.setTop(topBox);
        root.setCenter(center);
        root.setBottom(reminderBox);
        root.setLeft(leftBox);

        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.setTitle("Improve! Your App for sustainable development");
        if (iconPath != null) {
            stage.getIcons().add(new Image(iconPath));
        }
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}