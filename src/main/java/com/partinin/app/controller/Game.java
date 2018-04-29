package com.partinin.app.controller;

import com.partinin.app.model.BigField;
import com.partinin.app.model.Field;
import com.partinin.app.veiw.BigFieldView;
import com.partinin.app.veiw.FieldView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/** 1. Each turn, you mark one of the small squares.
 *  2. When you get three in a row on a small board, you’ve won that board.
 *  3. To win the game, you need to win three small boards in a row.
 *
 *  You don’t get to pick which of the nine boards to play on. That’s determined
 *  by your opponent’s previous move. Whichever square he picks, that’s the board
 *  you must play in next.
 *
 *  What if my opponent sends me to a board that’s already been won? In that case,
 *  congratulations – you get to go anywhere you like, on any of the other boards.
 *
 *  What if one of the small boards results in a tie? I recommend that the board
 *  counts for neither X nor O. But, if you feel like a crazy variant, you could
 *  agree before the game to count a tied board for both X and O.*/

public class Game extends Application {
    private static final int PANE_WIDTH = 700;
    private static final int PANE_HEIGHT = 700;
    static private BigField bigField = new BigField(); // Create a Field model
    static private BigFieldView view = new BigFieldView(bigField); // Create a pane view

    @Override
    public void start(Stage primaryStage) {
        //BorderPane pane = new BorderPane();
        pane.setCenter(view);

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, PANE_WIDTH, PANE_HEIGHT);
        primaryStage.setTitle("Tic-Tak-Toe"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public static void handleMouseClick(FieldView.Cell cell, FieldView fieldView, Field field) {
        field.setTokenInCells(cell.getPosition(), fieldView, cell);
        bigField.checkWinner();
    }

    public static BigField getBigField() {
        return bigField;
    }

    public static BigFieldView getBigFieldView() {
        return view;
    }
}
