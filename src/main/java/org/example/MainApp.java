package org.example;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        // Root
        BorderPane root = new BorderPane();

        // Zentrum: großer, fetter Schriftzug (skalierend)
        Label title = new Label("Improve");
        title.setStyle("-fx-font-weight: bold;");
        StackPane center = new StackPane(title);
        center.setPadding(new Insets(20));
        root.setCenter(center);

        // Oben rechts: Streak-Label (skalierend)
        Label streakLabel = new Label("Streak");
        streakLabel.setStyle("-fx-font-weight: bold; -fx-font-family: 'Segoe UI'; -fx-text-fill: #FC876A;");
        HBox topBox = new HBox(streakLabel);
        topBox.setPadding(new Insets(10));
        topBox.setAlignment(Pos.TOP_RIGHT);
        root.setTop(topBox);

        // Unten links: TextField und zwei Buttons nebeneinander (wachsen)
        TextField reminderField = new TextField();
        reminderField.setPromptText("Reminder");
        reminderField.setMaxWidth(Double.MAX_VALUE);

        Button tdlButton = new Button("To-Do-List");
        Button sleepTrackerButton = new Button("Sleep Tracker");

        HBox bottomBox = new HBox(50, reminderField, tdlButton, sleepTrackerButton);
        bottomBox.setPadding(new Insets(10, 20, 20, 20));
        bottomBox.setAlignment(Pos.CENTER_LEFT);
        // Set bottomBox alignment within BorderPane to bottom-left
        BorderPane.setAlignment(bottomBox, Pos.BOTTOM_LEFT);
        root.setBottom(bottomBox);

        // Scene
        Scene scene = new Scene(root, 800, 480);
        stage.setScene(scene);

        // Make controls grow horizontally
        HBox.setHgrow(reminderField, Priority.ALWAYS);
        // Buttons can grow proportionally to scene width
        tdlButton.setMaxWidth(Double.MAX_VALUE);
        sleepTrackerButton.setMaxWidth(Double.MAX_VALUE);

        // Bind button widths to a fraction of scene width (responsive)
        tdlButton.prefWidthProperty().bind(scene.widthProperty().multiply(0.12));
        sleepTrackerButton.prefWidthProperty().bind(scene.widthProperty().multiply(0.12));

        // Title font size scales with center size
        DoubleBinding titleSize = Bindings.createDoubleBinding(
            () -> Math.max(24, Math.min(center.getWidth() / 6.0, center.getHeight() / 1.5)),
            center.widthProperty(), center.heightProperty());
        title.styleProperty().bind(Bindings.createStringBinding(
            () -> "-fx-font-weight: bold; -fx-font-size: " + (int) titleSize.get() + "px;",
            titleSize));

        // Streak label font scales with scene width
        DoubleBinding streakSize = Bindings.createDoubleBinding(
            () -> Math.max(12, scene.getWidth() * 0.03),
            scene.widthProperty());
        streakLabel.styleProperty().bind(Bindings.createStringBinding(
            () -> "-fx-font-weight: bold; -fx-font-family: 'Segoe UI'; -fx-text-fill: #FC876A; -fx-font-size: " + (int) streakSize.get() + "px; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-padding: 5px;",
            streakSize));

        // Bottom controls font scales with scene height
        DoubleBinding bottomFont = Bindings.createDoubleBinding(
            () -> Math.max(12, scene.getHeight() * 0.05),
            scene.heightProperty());
        reminderField.styleProperty().bind(Bindings.createStringBinding(
            () -> "-fx-font-size: " + (int) bottomFont.get() + "px;",
            bottomFont));
        tdlButton.styleProperty().bind(Bindings.createStringBinding(
            () -> "-fx-font-size: " + (int) bottomFont.get() + "px;",
            bottomFont));
        sleepTrackerButton.styleProperty().bind(Bindings.createStringBinding(
            () -> "-fx-font-size: " + (int) bottomFont.get() + "px;",
            bottomFont));

        stage.setTitle("Improve! Your App for sustainable development");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}