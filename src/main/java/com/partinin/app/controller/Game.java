package com.partinin.app.controller;

import com.partinin.app.model.Field;
import com.partinin.app.veiw.FieldView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Game extends Application {
    public static final int PANE_WIDTH = 400;
    public static final int PANE_HEIGHT = 400;
    private Field field = new Field(); // Create a Field model

    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane pane = new BorderPane();
        FieldView view = new FieldView(field); // Create a FieldView pane view
        pane.setCenter(view);

        // Create a scene and place the pane in the stage
         Scene scene = new Scene(pane, PANE_WIDTH, PANE_HEIGHT);
         primaryStage.setTitle("Tic-Tak-Toe"); // Set the stage title
         primaryStage.setScene(scene); // Place the scene in the stage
         primaryStage.show(); // Display the stage
    }

    public static void handleMouseClick(FieldView.Cell cell, FieldView fieldView, Field field) {
        field.setTokenInCells(cell.getX(), cell.getY(), fieldView, cell);
    }
}