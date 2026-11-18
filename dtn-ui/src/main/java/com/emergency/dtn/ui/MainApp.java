package com.emergency.dtn.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Entry point for the DTN JavaFX application.
 *
 * Stage 1 placeholder — just opens an empty window to confirm
 * that JavaFX is wired up correctly.
 *
 * We will replace the contents of start() in Stage 10 with the
 * real network canvas, dashboard, and controls.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // A StackPane centres its single child in the window
        StackPane root = new StackPane();

        // Placeholder label — proves JavaFX is running
        Label label = new Label("Emergency DTN Simulator — Stage 1 ✓");
        root.getChildren().add(label);

        // Scene: the content tree + window dimensions (800 × 600 px)
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Emergency DTN Simulator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * main() is only needed when launching without the JavaFX launcher.
     * The javafx-maven-plugin calls launch() directly.
     */
    public static void main(String[] args) {
        launch(args);
    }
}

